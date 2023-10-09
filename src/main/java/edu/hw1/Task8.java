package edu.hw1;

import java.awt.Point;
import java.util.ArrayList;

public class Task8 {

    final static int BOARD_SIZE = 8;

    private static final int ATTACK_RADIUS = 3;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        if (board.length != BOARD_SIZE || board[0].length != BOARD_SIZE) {
            return false;
        }

        ArrayList<Point> knightPositionsArray = new ArrayList<>();

        for (int indexX = 0; indexX < BOARD_SIZE; indexX++) {
            for (int indexY = 0; indexY < BOARD_SIZE; indexY++) {
                if (board[indexX][indexY] == 1) {
                    knightPositionsArray.add(new Point(indexX, indexY));
                    for (int knightInArrayIndex = 0; knightInArrayIndex < knightPositionsArray.size() - 1;
                         knightInArrayIndex++) {
                        if (Math.abs(indexX - knightPositionsArray.get(knightInArrayIndex).x) != 0
                                && Math.abs(indexY - knightPositionsArray.get(knightInArrayIndex).y) != 0
                                && Math.abs(indexX - knightPositionsArray.get(knightInArrayIndex).x)
                                + Math.abs(indexY - knightPositionsArray.get(knightInArrayIndex).y) == ATTACK_RADIUS) {

                            return false;
                        }
                    }
                }

            }
        }
        return true;
    }
}
