package edu.hw9;

import edu.project2.Maze.Maze;
import edu.project2.Maze.MazeCell;
import edu.project2.MazeSolvers.MazeSolver;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DFSMultiThreadVersion implements MazeSolver {
    public DFSMultiThreadVersion() {
    }

    private static final int TRACE_CODE = 2;
    private static final int WALL_CODE = 1;

    @Override
    public int[][] mazeSearch(MazeCell begin, MazeCell end, Maze maze) {

        int[][] visited = new int[maze.width()][maze.height()];
        for (int i = 0; i < maze.width(); i++) {
            if (maze.height() >= 0) {
                System.arraycopy(maze.mazeMatrix[i], 0, visited[i], 0, maze.height());
            }
        }

        Map<MazeCell, MazeCell> previousCell = new ConcurrentHashMap<>();
        previousCell.put(begin, begin);

        dfs(begin, visited, previousCell);

        MazeCell current = end;
        while (current != begin) {
            visited[current.x()][current.y()] = TRACE_CODE;
            current = previousCell.get(current);
        }
        visited[current.x()][current.y()] = TRACE_CODE;
        return visited;
    }

    private static void dfs(MazeCell begin, int[][] visited, Map<MazeCell, MazeCell> previousCell) {

        MazeCell newCell = new MazeCell(begin.x() - 1, begin.y());
        if (!previousCell.containsKey(newCell) && visited[newCell.x()][newCell.y()] != WALL_CODE) {
            previousCell.put(newCell, begin);
            dfs(newCell, visited, previousCell);
        }

        newCell = new MazeCell(begin.x() + 1, begin.y());
        if (!previousCell.containsKey(newCell) && visited[newCell.x()][newCell.y()] != WALL_CODE) {
            previousCell.put(newCell, begin);
            dfs(newCell, visited, previousCell);
        }

        newCell = new MazeCell(begin.x(), begin.y() - 1);
        if (!previousCell.containsKey(newCell) && visited[newCell.x()][newCell.y()] != WALL_CODE) {
            previousCell.put(newCell, begin);
            dfs(newCell, visited, previousCell);
        }

        newCell = new MazeCell(begin.x(), begin.y() + 1);
        if (!previousCell.containsKey(newCell) && visited[newCell.x()][newCell.y()] != WALL_CODE) {
            previousCell.put(newCell, begin);
            dfs(newCell, visited, previousCell);
        }
    }
}
