package edu.project2.MazeRenders;

import edu.project2.MazeGame;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleMazeRender implements MazeRender {

    private static final String WALL = "███";
    private static final String EMPTY_PASSAGE = "   ";
    private static final String TRACE = " ● ";

    private static final Logger LOGGER = LogManager.getLogger(MazeGame.class);

    public ConsoleMazeRender() {
    }

    @Override
    public ArrayList<String> mazePrint(int[][] mazeMatrix) {
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
