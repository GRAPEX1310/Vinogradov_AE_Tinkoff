package edu.project4.Builders;

import edu.project4.Point.Pixel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImageBuilder extends JFrame {

    private final BufferedImage image;
    private static final Logger LOGGER = LogManager.getLogger();

    public ImageBuilder(int width, int height) {
        super("FRACTAL FLAME");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void renderFlame(Pixel[][] image) {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                createPixelInCell(x, y, image[x][y].getColor().getRGBValue());
                repaint();
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

    private void createPixelInCell(int x, int y, int color) {
        image.setRGB(x, y, color);
    }

    public void saveImage() {
        File file = new File(UUID.randomUUID() + ".png");
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }
}
