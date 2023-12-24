package edu.project2.Generators;

import edu.project2.Maze.Maze;
import edu.project2.Maze.MazeCell;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class DFSMazeGenerator implements  MazeGenerator {

    public DFSMazeGenerator() {

    }

    @Override
    public void mazeGenerate(Maze maze) {
        int[][] resultMazeMatrix = new int[maze.width()][maze.height()];
        boolean[][] visited = new boolean[maze.width()][maze.height()];

        for (int i = 0; i < maze.width(); i++) {
            for (int j = 0; j < maze.height(); j++) {
                if ((i % 2 != 0 && j % 2 != 0) && (i < maze.width() - 1 && j < maze.height() - 1)
                ) {
                    resultMazeMatrix[i][j] = 0;
                } else {
                    resultMazeMatrix[i][j] = 1;
                }
                visited[i][j] = false;
            }
        }

        MazeCell currentCell = new MazeCell(1, 1);
        MazeCell neighbourCell;
        visited[currentCell.x()][currentCell.y()] = true;
        Stack<MazeCell> cellStack = new Stack<>();

        do {
            MazeCell[] neighbours = getNeighbours(maze, currentCell, visited);
            if (neighbours.length != 0) {
                Random random = new Random();
                int randomNeighbourIndex = random.nextInt(neighbours.length);
                neighbourCell = neighbours[randomNeighbourIndex];
                cellStack.push(currentCell);
                removeWall(resultMazeMatrix, visited, currentCell, neighbourCell);
                currentCell = neighbourCell;
                visited[currentCell.x()][currentCell.y()] = true;

            } else if (!cellStack.empty()) {
                currentCell = cellStack.pop();

            } else {
                MazeCell[] unvisitedCells = getUnvisitedCells(visited);
                Random random = new Random();
                int randomNeighbourIndex = random.nextInt(unvisitedCells.length);
                currentCell = unvisitedCells[randomNeighbourIndex];
            }
        } while (visitCounter(visited) != 0);
        maze.mazeMatrix = resultMazeMatrix;
    }

    private static MazeCell[] getNeighbours(Maze maze, MazeCell currentCell, boolean[][] visited) {
        final int DISTANCE = 2;
        ArrayList<MazeCell> resultArray = new ArrayList<>();

        MazeCell top = null;
        MazeCell bottom = null;
        MazeCell right = null;
        MazeCell left = null;

        int delta1 = (maze.width() % 2 == 0) ? 2 : 1;
        int delta2 = (maze.height() % 2 == 0) ? 2 : 1;

        if (currentCell.x() + DISTANCE < maze.width() - delta1) {
            right = new MazeCell(currentCell.x() + DISTANCE, currentCell.y());
        }
        if (currentCell.x() - DISTANCE > 0) {
            left = new MazeCell(currentCell.x() - DISTANCE, currentCell.y());
        }
        if (currentCell.y() + DISTANCE < maze.height() - delta2) {
            bottom = new MazeCell(currentCell.x(), currentCell.y() + DISTANCE);
        }
        if (currentCell.y() - DISTANCE > 0) {
            top = new MazeCell(currentCell.x(), currentCell.y() - DISTANCE);
        }
        MazeCell[] possibleNeighbours = {top, bottom, right, left};

        for (MazeCell neighbour : possibleNeighbours) {
            if (neighbour != null && !visited[neighbour.x()][neighbour.y()]) {
                resultArray.add(neighbour);
            }
        }

        return resultArray.toArray(new MazeCell[0]);
    }

    private static void removeWall(int[][] matrix, boolean[][] visited, MazeCell first, MazeCell second) {
        int diffX = second.x() - first.x();
        int diffY = second.y() - first.y();

        int addX = (diffX != 0) ? (diffX / Math.abs(diffX)) : 0;
        int addY = (diffY != 0) ? (diffY / Math.abs(diffY)) : 0;

        MazeCell target = new MazeCell(first.x() + addX, first.y() + addY);
        visited[target.x()][target.y()] = true;
        matrix[target.x()][target.y()] = 0;
    }

    private static MazeCell[] getUnvisitedCells(boolean[][] visited) {
        ArrayList<MazeCell> unvisitedCells = new ArrayList<>();

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if ((i % 2 != 0 && j % 2 != 0)
                        && (i < visited.length - 1 && j < visited[i].length - 1) && !visited[i][j]) {
                    unvisitedCells.add(new MazeCell(i, j));
                }
            }
        }
        return unvisitedCells.toArray(new MazeCell[0]);
    }

    private static int visitCounter(boolean[][] visited) {
        int counter = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if ((i % 2 != 0 && j % 2 != 0)
                        && (i < visited.length - 1 && j < visited[i].length - 1) && !visited[i][j]) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
