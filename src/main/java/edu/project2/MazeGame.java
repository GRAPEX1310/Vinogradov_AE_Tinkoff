package edu.project2;

import edu.project2.Generators.DFSMazeGenerator;
import edu.project2.Maze.Maze;
import edu.project2.Maze.MazeCell;
import edu.project2.MazeSolvers.MazeBFSSearch;
import edu.project2.MazeSolvers.MazeDFSSearch;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeGame {

    public MazeGame() {

    }

    private static final String GREETING_MESSAGE = """
            ====HELLO USER====
            This is a maze generation program
            """;
    private static final String CHOOSE_SIZE = """
            Choose the size of the maze:
            """;
    private static final String CHOOSE_START_CELL = """
            Then choose the starting point (the value is greater than 0):
            """;
    private static final String CHOOSE_FINISH_CELL = """
            And the ending point (the value is 2 less than the size of the maze):
            """;
    private static final String PRINT_GENERATED_MAZE = """
            ====GENERATED MAZE====
            """;
    private static final String CHOOSE_SOLVER_TYPE = """
            Choose the type of MazeSolver: BFS/DFS(0/1):
            """;
    private static final String PRINT_SOLVED_MAZE = """
            ====SOLVED MAZE====
            """;

    private static final String WALL = "███";
    private static final String EMPTY_PASSAGE = "   ";
    private static final String TRACE = " ● ";


    private static final Logger LOGGER = LogManager.getLogger(MazeGame.class);
    private static final Scanner SCANNER = new Scanner(System.in);

    public void startMazeGenerate() {
        LOGGER.info(GREETING_MESSAGE);
        LOGGER.info(CHOOSE_SIZE);
        int[] mazeSize = mazeInput();

        LOGGER.info(CHOOSE_START_CELL);
        int[] mazeStartCell = mazeInput();
        MazeCell startCell = new MazeCell(mazeStartCell[0], mazeStartCell[1]);

        LOGGER.info(CHOOSE_FINISH_CELL);
        int[] mazeFinishCell = mazeInput();
        MazeCell finishCell = new MazeCell(mazeFinishCell[0], mazeFinishCell[1]);

        Maze maze = new Maze(mazeSize[0], mazeSize[1]);
        DFSMazeGenerator.mazeGenerate(maze);
        mazeRender(maze.mazeMatrix);

        int[][] solvedMaze = mazeSolving(maze, startCell, finishCell);
        mazeRender(solvedMaze);

    }

    public static int[][] mazeSolving(Maze maze, MazeCell startCell, MazeCell finishCell) {
        LOGGER.info(CHOOSE_SOLVER_TYPE);
        int mazeSolverType = SCANNER.nextInt();

        int[][] solvedMaze = new int[0][0];
        switch (mazeSolverType) {
            case 0:
                solvedMaze = MazeBFSSearch.mazeBFSSearch(startCell, finishCell, maze);
                break;
            case 1:
                solvedMaze = MazeDFSSearch.mazeDFSSearch(startCell, finishCell, maze);
                break;
            default:
                break;
        }
        return solvedMaze;
    }

    private static int[] mazeInput() {
        int[] mazeStats = new int[2];

        int value1 = SCANNER.nextInt();
        int value2 = SCANNER.nextInt();

        mazeStats[0] = value1;
        mazeStats[1] = value2;

        return mazeStats;
    }

    public static ArrayList<String> mazeRender(int[][] mazeMatrix) {
        ArrayList<String> renderMazeMatrix = new ArrayList<>();
        for (int[] line : mazeMatrix) {
            String mazeLine = createMazeLine(line);
            LOGGER.info(mazeLine);
            renderMazeMatrix.add(mazeLine);
        }
        return renderMazeMatrix;
    }

    private static String createMazeLine(int[] mazeLine) {
        StringBuilder resultMazeLine = new StringBuilder();

        for (int mazeCell : mazeLine) {
            if (mazeCell == 1) {
                resultMazeLine.append(WALL);
            } else if (mazeCell == 2) {
                resultMazeLine.append(TRACE);
            } else {
                resultMazeLine.append(EMPTY_PASSAGE);
            }
        }
        return resultMazeLine.toString();
    }
}
