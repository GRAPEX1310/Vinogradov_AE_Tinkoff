package edu.project2.MazeSolvers;

import edu.project2.Maze.Maze;
import edu.project2.Maze.MazeCell;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MazeBFSSearch {
    private MazeBFSSearch() {

    }

    private static final int TRACE_CODE = 2;
    private static final int WALL_CODE = 1;

    public static int[][] mazeBFSSearch(MazeCell begin, MazeCell end, Maze maze) {

        int[][] visited = new int[maze.width()][maze.height()];
        for (int i = 0; i < maze.width(); i++) {
            if (maze.height() >= 0) {
                System.arraycopy(maze.mazeMatrix[i], 0, visited[i], 0, maze.height());
            }
        }

        Map<MazeCell, MazeCell> previousCell = bfs(begin, end, visited);

        MazeCell current = end;
        while (current != begin) {
            visited[current.x()][current.y()] = TRACE_CODE;
            current = previousCell.get(current);
        }
        visited[current.x()][current.y()] = TRACE_CODE;
        return visited;
    }

    private static Map<MazeCell, MazeCell> bfs(MazeCell begin, MazeCell end, int[][] visited) {
        HashMap<MazeCell, MazeCell> previousCell = new HashMap<>();
        previousCell.put(begin, begin);
        Deque<MazeCell> deque = new ArrayDeque<>();
        deque.addLast(begin);

        while (!deque.isEmpty() && !previousCell.containsKey(end)) {
            MazeCell top = deque.getFirst();
            deque.pollFirst();

            MazeCell newCell = new MazeCell(top.x() - 1, top.y());
            if (!previousCell.containsKey(newCell) && visited[newCell.x()][newCell.y()] != WALL_CODE) {
                previousCell.put(newCell, top);
                deque.addLast(newCell);
            }

            newCell = new MazeCell(top.x() + 1, top.y());
            if (!previousCell.containsKey(newCell) && visited[newCell.x()][newCell.y()] != WALL_CODE) {
                previousCell.put(newCell, top);
                deque.addLast(newCell);
            }

            newCell = new MazeCell(top.x(), top.y() - 1);
            if (!previousCell.containsKey(newCell) && visited[newCell.x()][newCell.y()] != WALL_CODE) {
                previousCell.put(newCell, top);
                deque.addLast(newCell);
            }

            newCell = new MazeCell(top.x(), top.y() + 1);
            if (!previousCell.containsKey(newCell) && visited[newCell.x()][newCell.y()] != WALL_CODE) {
                previousCell.put(newCell, top);
                deque.addLast(newCell);
            }
        }
        return previousCell;
    }
}
