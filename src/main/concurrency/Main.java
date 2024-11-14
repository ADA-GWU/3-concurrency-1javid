package src.main.concurrency;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import src.main.concurrency.classes.ImagePanel;
import src.main.concurrency.utils.Utils;

class Main {
    
    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Please provide the required arguments: file name, square size, and processing mode ('S' or 'M').");
            return;
        }
        
        BufferedImage img = Utils.loadImage(args[0]);
        int squareSize = Utils.parseSquareSize(args[1]);
        
        Dimension screenSize = Utils.getScreenSize();
        Dimension imgSize = Utils.adjustImageSizeToScreen(img, screenSize);
        
        ImagePanel imagePanel = Utils.createImage(img, imgSize);

        Utils.processImage(args[2], img, imagePanel, squareSize);
    }
}