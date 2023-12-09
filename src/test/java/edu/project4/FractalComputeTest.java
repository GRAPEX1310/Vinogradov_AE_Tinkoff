package edu.project4;

import edu.project4.Builders.FlameBuilder;
import edu.project4.Point.Pixel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FractalComputeTest {

    private static final int RESOLUTION_X = 1920;
    private static final int RESOLUTION_Y = 1080;

    @Test
    @DisplayName("Fractal multi thread compute test")
    void testFractalMultiThreadCompute() {
        Pixel[][] defaultMatrix = new Pixel[RESOLUTION_X][RESOLUTION_Y];

        for (int x = 0; x < RESOLUTION_X; x++) {
            for (int y = 0; y < RESOLUTION_Y; y++) {
                defaultMatrix[x][y] = new Pixel();
            }
        }

        FlameBuilder flame = new FlameBuilder(1000, 100, 2.2, false,
                6, RESOLUTION_X, RESOLUTION_Y);

        flame.computeFractalFlame();
        boolean isDifferent = isDifferent(flame, defaultMatrix);

        assertThat(isDifferent).isTrue();
    }

    //idea from IDEA
    private static boolean isDifferent(FlameBuilder flame, Pixel[][] defaultMatrix) {
        Pixel[][] computedMatrix = flame.getMatrix();

        boolean isDifferent = false;

        for (int x = 0; x < RESOLUTION_X; x++) {
            for (int y = 0; y < RESOLUTION_Y; y++) {
                if (defaultMatrix[x][y].getColor().getRed() != computedMatrix[x][y].getColor().getRed()
                        || defaultMatrix[x][y].getColor().getGreen() != computedMatrix[x][y].getColor().getGreen()
                        || defaultMatrix[x][y].getColor().getBlue() != computedMatrix[x][y].getColor().getBlue()) {
                    isDifferent = true;
                    break;
                }
            }
        }
        return isDifferent;
    }

    @Test
    @DisplayName("Fractal gamma correction test")
    void testFractalGammaCorrection() {
        FlameBuilder flame = new FlameBuilder(1000, 100, 2.2, false,
                6, RESOLUTION_X, RESOLUTION_Y);
        flame.computeFractalFlame();

        Pixel[][] result = flame.gammaCorrection(flame.getMatrix());

        boolean isNotBlack = false;

        for (int x = 0; x < RESOLUTION_X; x++) {
            for (int y = 0; y < RESOLUTION_Y; y++) {
                if (result[x][y].getColor().getRed() != 0 || result[x][y].getColor().getGreen() != 0
                        || result[x][y].getColor().getBlue() != 0) {
                    isNotBlack = true;
                    break;
                }
            }
        }

        assertThat(isNotBlack).isTrue();
    }
}
