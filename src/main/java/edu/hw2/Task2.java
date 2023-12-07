package edu.hw2;

public class Task2 {
    public static class Rectangle {
        private int width;
        private int height;

        public Rectangle setWidth(int width) {
            this.width = width;
            return this;
        }

        public Rectangle setHeight(int height) {
            this.height = height;
            return this;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {
        @Override
        public Rectangle setWidth(int width) {
            Rectangle resultRectangle = new Rectangle();
            resultRectangle.setWidth(width);
            resultRectangle.setHeight(resultRectangle.getHeight());
            return resultRectangle;

        }

        @Override
        public Rectangle setHeight(int height) {
            Rectangle resultRectangle = new Rectangle();
            resultRectangle.setHeight(height);
            resultRectangle.setWidth(resultRectangle.getWidth());
            return resultRectangle;
        }

        public void setSide(int sideLen) {
            this.setHeight(sideLen);
            this.setWidth(sideLen);
        }
    }

}
