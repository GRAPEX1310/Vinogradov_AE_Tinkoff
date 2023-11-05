package edu.project2;

import edu.project2.Maze.Maze;
import edu.project2.Maze.MazeCell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MazeGameMethodsTest {
    @Test
    @DisplayName("Test mazeRender function")
    void testMazeRender() {
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

        ArrayList<String> correctMaze = new ArrayList<>(List.of(
                "█████████████████████████████████████████████",
                "███               ███   ███               ███",
                "███████████████   ███   ███   ███   █████████",
                "███               ███   ███   ███         ███",
                "███   ███████████████   ███   █████████   ███",
                "███   ███                           ███   ███",
                "███   ███   ███████████████████████████   ███",
                "███   ███         ███               ███   ███",
                "███   ███████████████   █████████   ███   ███",
                "███               ███   ███   ███   ███   ███",
                "███████████████   ███   ███   ███   ███   ███",
                "███         ███         ███   ███         ███",
                "███   ███   ███████████████   █████████   ███",
                "███   ███                                 ███",
                "█████████████████████████████████████████████"
        ));

        assertThat(MazeGame.mazeRender(maze.mazeMatrix)).isEqualTo(correctMaze);
    }

    @Test
    @DisplayName("Test mazeSolver function")
    void testMazeSolver() {
        Maze maze = new Maze(15, 15);

        maze.mazeMatrix = new int[][]{
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

        ArrayList<String> correctMaze = new ArrayList<>(List.of(
                "█████████████████████████████████████████████",
                "███ ●  ●  ●  ●  ● ███   ███               ███",
                "███████████████ ● ███   ███   ███   █████████",
                "███ ●  ●  ●  ●  ● ███   ███   ███         ███",
                "███ ● ███████████████   ███   █████████   ███",
                "███ ● ███                           ███   ███",
                "███ ● ███   ███████████████████████████   ███",
                "███ ● ███         ███ ●  ●  ●  ●  ● ███   ███",
                "███ ● ███████████████ ● █████████ ● ███   ███",
                "███ ●  ●  ●  ●  ● ███ ● ███   ███ ● ███   ███",
                "███████████████ ● ███ ● ███   ███ ● ███   ███",
                "███         ███ ●  ●  ● ███   ███ ●  ●  ● ███",
                "███   ███   ███████████████   █████████ ● ███",
                "███   ███                               ● ███",
                "█████████████████████████████████████████████"

        ));

        MazeCell start = new MazeCell(1, 1);
        MazeCell finish = new MazeCell(13, 13);

        assertThat(MazeGame.mazeRender(maze.mazeMatrix)).isEqualTo(correctMaze);
    }
}
