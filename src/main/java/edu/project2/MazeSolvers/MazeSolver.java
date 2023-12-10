package edu.project2.MazeSolvers;

import edu.project2.Maze.Maze;
import edu.project2.Maze.MazeCell;

public interface MazeSolver {

    int[][] mazeSearch(MazeCell begin, MazeCell end, Maze maze);
}
