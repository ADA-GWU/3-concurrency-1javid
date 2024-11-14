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

        if (args.length < 3) {
            System.out.println("Please provide the required arguments: file name, square size, and processing mode ('S' or 'M').");
            return;
        }
        
        BufferedImage img = ImageFileUtils.loadImage(args[0]);
        int squareSize = ScreenUtils.parseSquareSize(args[1]);
        
        Dimension screenSize = ScreenUtils.getScreenSize();
        Dimension imgSize = ScreenUtils.adjustImageSizeToScreen(img, screenSize);
        
        ImagePanel imagePanel = ImagePanelUtils.createImagePanel(img, imgSize);

        if ("S".equalsIgnoreCase(args[2])) {
            ThreadingUtils.processImageSingleThread(img, imagePanel, squareSize);
        } else if ("M".equalsIgnoreCase(args[2])) {
            ThreadingUtils.processImageMultiThread(img, imagePanel, squareSize);
        }
    }
}