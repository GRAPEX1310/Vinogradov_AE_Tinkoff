package edu.project4;

import edu.project4.Builders.FlameBuilder;
import edu.project4.Builders.ImageBuilder;
import edu.project4.Point.Pixel;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"UncommentedMain", "MagicNumber"})
public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] ags) {
        FlameBuilder flame = new FlameBuilder(10_000, 10_00, 2.2,
                true, 6, 1920, 1080);

        ImageBuilder imageBuilder = new ImageBuilder(1920, 1080);
        flame.computeFractalFlame();
        imageBuilder.renderFlame(flame.getMatrix());

        Pixel[][] image = flame.gammaCorrection(flame.getMatrix());
        imageBuilder.renderFlame(image);

        boolean save;
        LOGGER.info("Wanna save? (1/0)");
        Scanner scanner = new Scanner(System.in);
        save = Boolean.parseBoolean(scanner.nextLine());
        if (save) {
            imageBuilder.saveImage();
        }
    }
}
