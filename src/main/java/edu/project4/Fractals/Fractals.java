package edu.project4.Fractals;

import edu.project4.Point.Cell;

@SuppressWarnings("MagicNumber")
public class Fractals {

    private Fractals() {
    }

    public static final Fractal SINUSOIDAL = ((coefficients, cell) -> new Cell(Math.sin(cell.x()), Math.sin(cell.y())));

    public static final Fractal SPHERICAL =
            (coefficients, cell) -> {
                double radiusSquared = cell.x() * cell.x() + cell.y() * cell.y();
                return new Cell(cell.x() / radiusSquared, cell.y() / radiusSquared);
            };

    public static final Fractal POLAR = ((coefficients, cell) -> {
        double newX = Math.atan(cell.y() / cell.x()) / Math.PI;
        double newY = Math.sqrt(cell.x() * cell.x() + cell.y() * cell.y()) - 1;
        return new Cell(newX, newY);
    });

    public static final Fractal HEART = ((coefficients, cell) -> {
        double radius = Math.sqrt(cell.x() * cell.x() + cell.y() * cell.y());
        double cellAtan = Math.atan(cell.y()) / cell.x();
        double newX = radius * Math.sin(radius * cellAtan);
        double newY = (-1) * radius * Math.cos(radius * cellAtan);
        return new Cell(newX, newY);
    });

    public static final Fractal DISC = ((coefficients, cell) -> {
        double value = Math.sqrt(cell.x() * cell.x() + cell.y() * cell.y()) * Math.PI;
        double cellAtan = Math.atan(cell.y()) / cell.x();
        double pieceOfPi = 1 / Math.PI;
        double newX = pieceOfPi * cellAtan * Math.sin(value);
        double newY = pieceOfPi * cellAtan * Math.cos(value);
        return new Cell(newX, newY);
    });
}
