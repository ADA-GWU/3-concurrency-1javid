package src.main.concurrency.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class ScreenUtils {

    public static int parseSquareSize(String squareSizeArg) {
        return Integer.parseInt(squareSizeArg);
    }

    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static Dimension adjustImageSizeToScreen(BufferedImage img, Dimension screenSize) {
        int newHeight = img.getHeight(), newWidth = img.getWidth();
        System.out.println("Screen Size: " + screenSize.height + "x" + screenSize.width);
        System.out.println("Current Image Size: " + img.getHeight() + "x" + img.getWidth());
        if (screenSize.height < newHeight) newHeight = screenSize.height;
        if (screenSize.width < newWidth) newWidth = screenSize.width;
        System.out.println("Updated Frame Size: " + newHeight + "x" + newWidth);
        return new Dimension(newHeight, newWidth);
    }
}
