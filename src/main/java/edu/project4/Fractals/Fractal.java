package edu.project4.Fractals;

import edu.project4.Point.Cell;
import edu.project4.Utils.AthensCoefficients;

@FunctionalInterface
public interface Fractal {
    Cell apply(AthensCoefficients coefficients, Cell cell);
}
