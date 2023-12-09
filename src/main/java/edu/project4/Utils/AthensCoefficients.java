package edu.project4.Utils;

import java.util.concurrent.ThreadLocalRandom;

public record AthensCoefficients(double a, double b, double c, double d, double e, double f) {
    public static AthensCoefficients getCoefficients() {
        while (true) {
            double a = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            double b = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            double c = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            double d = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            double e = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            double f = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);

            if (validationCoefficients(a, b, d, e)) {
                return new AthensCoefficients(a, b, c, d, e, f);
            }
        }
    }

    private static boolean validationCoefficients(double a, double b, double d, double e) {
        return (a * a + d * d < 1) && (b * b + e * e < 1)
                && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));
    }
}
