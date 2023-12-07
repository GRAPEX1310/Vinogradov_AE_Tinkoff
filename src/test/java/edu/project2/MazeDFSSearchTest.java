package edu.project2;

import edu.project2.Maze.Maze;
import edu.project2.Maze.MazeCell;
import edu.project2.MazeSolvers.MazeDFSSearch;
import edu.project2.MazeSolvers.MazeSolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MazeDFSSearchTest {

    @Test
    @DisplayName("Test mazeDFSSearch function")
    void testMazeDFSSearch() {
        Maze maze = new Maze(15, 15);

        maze.mazeMatrix = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}

        };

        int[][] solvedMatrix = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 2, 2, 2, 2, 1, 0, 1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 2, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                {1, 2, 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 2, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                {1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 2, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                {1, 2, 1, 0, 0, 0, 1, 2, 2, 2, 2, 2, 1, 0, 1},
                {1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 0, 1},
                {1, 2, 2, 2, 2, 2, 1, 2, 1, 0, 1, 2, 1, 0, 1},
                {1, 1, 1, 1, 1, 2, 1, 2, 1, 0, 1, 2, 1, 0, 1},
                {1, 0, 0, 0, 1, 2, 2, 2, 1, 0, 1, 2, 2, 2, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 2, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}

        };


        MazeCell start = new MazeCell(1, 1);
        MazeCell finish = new MazeCell(13, 13);
        MazeSolver mazeSolver = new MazeDFSSearch();
        assertThat(mazeSolver.mazeSearch(start, finish, maze)).isEqualTo(solvedMatrix);
    }
}
