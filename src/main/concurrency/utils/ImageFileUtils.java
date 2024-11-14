package src.main.concurrency.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageFileUtils {

    public static BufferedImage loadImage(String filePath) {
        try {
            File imgPath = new File(filePath);
            return ImageIO.read(imgPath);
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
            return null;
        }
    }
}
