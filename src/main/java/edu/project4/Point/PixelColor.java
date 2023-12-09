package edu.project4.Point;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("MagicNumber")
public class PixelColor {
    private int red;
    private int green;
    private int blue;

    public PixelColor() {
        red = ThreadLocalRandom.current().nextInt(256);
        green = ThreadLocalRandom.current().nextInt(256);
        blue = ThreadLocalRandom.current().nextInt(256);
    }

    public PixelColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getRGBValue() {
        return (red << 16 | green << 8 | blue);
    }
}
