package edu.project4.Functions;

import edu.project4.Point.PixelColor;
import edu.project4.Utils.AthensCoefficients;
import edu.project4.Utils.FractalType;
import java.util.List;

public record Function(AthensCoefficients coefficients, AthensCoefficients transformation,
                       PixelColor pixelColor, List<FractalType> types) {
}
