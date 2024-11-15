package src.main.concurrency;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import src.main.concurrency.classes.ImagePanel;
import src.main.concurrency.utils.ImageFileUtils;
import src.main.concurrency.utils.ImagePanelUtils;
import src.main.concurrency.utils.ScreenUtils;
import src.main.concurrency.utils.ThreadingUtils;

class Main {

    public static void main(String[] args) {

        // Check if the required arguments are provided (file name, square size, and processing mode)
        if (args.length < 3) {
            System.out.println("Please provide the required arguments: file name, square size, and processing mode ('S' or 'M').");
            return;
        }
        
        // Load the image from the specified file path
        BufferedImage img = ImageFileUtils.loadImage(args[0]);

        // Parse the square size from the second argument
        int squareSize = ScreenUtils.parseSquareSize(args[1]);
        
        // Get the screen size and adjust the image size to fit the screen dimensions
        Dimension screenSize = ScreenUtils.getScreenSize();
        Dimension imgSize = ScreenUtils.adjustImageSizeToScreen(img, screenSize);
        
        // Create an image panel to display the image with the adjusted size
        ImagePanel imagePanel = ImagePanelUtils.createImagePanel(img, imgSize);

        // Check if the processing mode is single-threaded ('S') or multi-threaded ('M')
        if ("S".equalsIgnoreCase(args[2])) {
            ThreadingUtils.processImageSingleThread(img, imagePanel, squareSize);
        } else if ("M".equalsIgnoreCase(args[2])) {
            ThreadingUtils.processImageMultiThread(img, imagePanel, squareSize);
        }
    }
}