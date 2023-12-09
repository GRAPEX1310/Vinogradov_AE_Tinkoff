package edu.project4.Point;

public class Pixel {
    private PixelColor pixelColor;
    private int hitCounter;
    private double normal;

    public Pixel() {
        this.pixelColor = new PixelColor(0, 0, 0);
        this.hitCounter = 0;
        this.normal = 0;
    }

    public int getHitCounter() {
        return hitCounter;
    }

    public void incrementHitCounter() {
        hitCounter++;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public PixelColor getColor() {
        return pixelColor;
    }
}
