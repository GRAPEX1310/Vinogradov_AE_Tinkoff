package edu.project4.Functions;

import edu.project4.Fractals.Fractals;
import edu.project4.Point.PixelColor;
import edu.project4.Utils.AthensCoefficients;
import edu.project4.Utils.FractalType;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Functions {

    private Functions() {
    }

    public static Function getFunction() {
        return FUNCTION_LIST.get(ThreadLocalRandom.current().nextInt(FUNCTION_LIST.size()));
    }

    private static final List<Function> FUNCTION_LIST = List.of(
            new Function(AthensCoefficients.getCoefficients(),
                    AthensCoefficients.getCoefficients(),
                    new PixelColor(),
                    List.of(
                            new FractalType(ThreadLocalRandom.current().nextInt(1, 3), Fractals.DISC),
                            new FractalType(ThreadLocalRandom.current().nextInt(1, 3), Fractals.SINUSOIDAL),
                            new FractalType(ThreadLocalRandom.current().nextInt(1, 3), Fractals.HEART)
                    )
            ),
            new Function(AthensCoefficients.getCoefficients(),
                    AthensCoefficients.getCoefficients(),
                    new PixelColor(),
                    List.of(
                            new FractalType(ThreadLocalRandom.current().nextInt(1, 3), Fractals.SPHERICAL),
                            new FractalType(ThreadLocalRandom.current().nextInt(1, 3), Fractals.POLAR)
                    )
            ),
            new Function(AthensCoefficients.getCoefficients(),
                    AthensCoefficients.getCoefficients(),
                    new PixelColor(),
                    List.of(
                            new FractalType(ThreadLocalRandom.current().nextInt(1, 3), Fractals.POLAR),
                            new FractalType(ThreadLocalRandom.current().nextInt(1, 3), Fractals.SINUSOIDAL)
                    )
            ),
            new Function(AthensCoefficients.getCoefficients(),
                    AthensCoefficients.getCoefficients(),
                    new PixelColor(),
                    List.of(
                            new FractalType(ThreadLocalRandom.current().nextInt(1, 3), Fractals.DISC),
                            new FractalType(ThreadLocalRandom.current().nextInt(1, 3), Fractals.SPHERICAL)
                    )
            ),
            new Function(AthensCoefficients.getCoefficients(),
                    AthensCoefficients.getCoefficients(),
                    new PixelColor(),
                    List.of(
                            new FractalType(ThreadLocalRandom.current().nextInt(1, 3), Fractals.HEART),
                            new FractalType(ThreadLocalRandom.current().nextInt(1, 3), Fractals.SPHERICAL)
                    )
            )
    );
}
