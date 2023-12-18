package edu.project2.Maze;

public final class Maze {

    private int width;
    private int height;
    public int[][] mazeMatrix;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

}
