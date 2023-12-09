package edu.project4.Builders;

import edu.project4.Functions.Function;
import edu.project4.Functions.Functions;
import edu.project4.Point.Cell;
import edu.project4.Point.Pixel;
import edu.project4.Utils.FractalType;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlameBuilder {

    private final ReentrantLock lock = new ReentrantLock();
    private final ExecutorService executorService;

    private static final double X_MIN = -1.777;
    private static final double X_MAX = 1.777;
    private static final double Y_MIN = -1;
    private static final double Y_MAX = 1;
    private static final int MINIMUM_ITERATIONS = 20;
    private final int iterations;
    private final double gamma;
    private final boolean symmetry;
    private final int threadsCount;
    private final int samplesPerThread;
    private final int resolutionX;
    private final int resolutionY;

    private final Pixel[][] matrix;

    public FlameBuilder(int samplesAmount, int iterations, double gamma, boolean symmetry,
                        int threadsAmount, int resolutionX, int resolutionY) {
        this.iterations = iterations;
        this.gamma = gamma;
        this.symmetry = symmetry;
        this.threadsCount = threadsAmount;
        this.samplesPerThread = samplesAmount / threadsCount;
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;

        executorService = Executors.newFixedThreadPool(threadsCount);

        matrix = new Pixel[resolutionX][resolutionY];

        for (int i = 0; i < resolutionX; ++i) {
            for (int j = 0; j < resolutionY; ++j) {
                matrix[i][j] = new Pixel();
            }
        }
    }

    public void computeFractalFlame() {
        var tasks = Stream.generate(() -> CompletableFuture.runAsync(this::threadCompute, executorService))
                .limit(threadsCount)
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
    }

    public Pixel[][] gammaCorrection(Pixel[][] matrix) {
        var tasks = IntStream.range(0, threadsCount)
                .mapToObj(threads -> CompletableFuture.runAsync(
                        () -> {
                            double max = 0;

                            for (int x = threads; x < resolutionX; x += threadsCount) {
                                for (int y = 0; y < resolutionY; ++y) {
                                    if (matrix[x][y].getHitCounter() != 0) {
                                        matrix[x][y].setNormal(Math.log10(matrix[x][y].getHitCounter()));

                                        if (matrix[x][y].getNormal() > max) {
                                            max = matrix[x][y].getNormal();
                                        }
                                    }
                                }
                            }

                            for (int x = threads; x < resolutionX; x += threadsCount) {
                                for (int y = 0; y < resolutionY; ++y) {
                                    matrix[x][y].setNormal(matrix[x][y].getNormal() / max);

                                    double gammaCoefficient = Math.pow(matrix[x][y].getNormal(), (1.0 / gamma));

                                    matrix[x][y].getColor().setRed((int)
                                            (matrix[x][y].getColor().getRed() * gammaCoefficient));

                                    matrix[x][y].getColor().setGreen((int)
                                            (matrix[x][y].getColor().getGreen() * gammaCoefficient));

                                    matrix[x][y].getColor().setBlue((int)
                                            (matrix[x][y].getColor().getBlue() * gammaCoefficient));
                                }
                            }
                        }, executorService
                ))
                .limit(threadsCount)
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(tasks).join();
        return matrix;
    }

    private void threadCompute() {
        for (int i = 0; i < samplesPerThread; ++i) {
            double newX = ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX);
            double newY = ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX);

            for (int j = -MINIMUM_ITERATIONS; j < iterations; ++j) {
                Function function = Functions.getFunction();
                Cell cell = transformateCell(function, newX, newY, j);

                newX = cell.x();
                newY = cell.y();

                if (j >= 0 && isInRange(cell)) {
                    Cell newCell = getDisplayPosition(cell);

                    if (isPositionOnDisplay(newCell)) {
                        try {
                            lock.lock();

                            int x = (int) newCell.x();
                            int y = (int) newCell.y();

                            matrix[x][y].incrementHitCounter();

                            matrix[x][y].getColor().setRed(
                                    (matrix[x][y].getColor().getRed() + function.pixelColor().getRed()) / 2);

                            matrix[x][y].getColor().setGreen(
                                    (matrix[x][y].getColor().getGreen() + function.pixelColor().getGreen()) / 2);

                            matrix[x][y].getColor().setBlue(
                                    (matrix[x][y].getColor().getBlue() + function.pixelColor().getBlue()) / 2);
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("MagicNumber")
    private Cell transformateCell(Function function, double x, double y, int iteration) {

        double newX = 0;
        double newY = 0;

        double currentX = function.coefficients().a() * x
                + function.coefficients().b() * y + function.coefficients().c();

        double currentY = function.coefficients().d() * x
                + function.coefficients().e() * y + function.coefficients().f();

        List<FractalType> types = function.types();
        for (FractalType type : types) {
            Cell cell = type.type().apply(function.coefficients(), new Cell(currentX, currentY));

            newX = type.weight() * cell.x();
            newY = type.weight() * cell.y();
        }

        double resultX = function.transformation().a() * newX
                + function.transformation().b() * newY + function.transformation().c();

        double resultY = function.transformation().d() * newX
                + function.transformation().e() * newY + function.transformation().f();

        if (symmetry) {
            if (iteration % 4 == 0) {
                resultX *= -1;
            } else if (iteration % 3 == 0) {
                resultX *= -1;
                resultY *= -1;
            } else if (iteration % 2 == 0) {
                resultY *= -1;
            }
        }

        return new Cell(resultX, resultY);
    }

    private boolean isInRange(Cell cell) {
        return X_MIN <= cell.x() && cell.x() <= X_MAX && Y_MIN <= cell.y() && cell.y() <= Y_MAX;
    }

    private Cell getDisplayPosition(Cell cell) {
        return new Cell(
                (cell.x() - X_MIN) / (X_MAX - X_MIN) * resolutionX,
                (cell.y() - Y_MIN) / (Y_MAX - Y_MIN) * resolutionY
        );
    }

    private boolean isPositionOnDisplay(Cell cell) {
        return 0 <= cell.x() && cell.x() <= resolutionX
                && 0 <= cell.y() && cell.y() <= resolutionY;
    }

    public Pixel[][] getMatrix() {
        return matrix;
    }
}
