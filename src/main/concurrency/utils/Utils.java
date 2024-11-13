package src.main.concurrency.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import src.main.concurrency.classes.ImagePanel;
import src.main.concurrency.classes.SingleThread;

public class Utils {
    
    public static List<Integer> colorAverage(List<List<Integer>> rgbList) {
        Integer r = 0, g = 0, b = 0;

        for (List<Integer> rgb : rgbList) {
            r += rgb.get(0);
            g += rgb.get(1);
            b += rgb.get(2);
        }

        int size = rgbList.size();

        r /= size;
        g /= size;
        b /= size;
    
        return Arrays.asList(r, g, b);
    }

    public static BufferedImage loadImage(String filePath) {
        try {
            File imgPath = new File(filePath);
            return ImageIO.read(imgPath);
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
            return null;
        }
    }    

    public static int parseSquareSize(String squareSizeArg) {
        return Integer.parseInt(squareSizeArg);
    }

    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static Dimension adjustImageSizeToScreen(BufferedImage img, Dimension screenSize) {
        int imgHeight = Math.min(img.getHeight(), screenSize.height);
        int imgWidth = Math.min(img.getWidth(), screenSize.width);
        return new Dimension(imgWidth, imgHeight);
    }

    public static JFrame createImageFrame(BufferedImage img, Dimension imgSize) {
        JFrame frame = new JFrame();
        ImagePanel imagePanel = new ImagePanel(img);
        frame.add(imagePanel);
        frame.setSize(imgSize.width, imgSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    public static void processImage(String mode, BufferedImage img, ImagePanel imagePanel, int squareSize) {
        if ("S".equalsIgnoreCase(mode)) {
            Thread blurThread = new Thread(() -> {
                SingleThread singleThread = new SingleThread(img, imagePanel, squareSize);
                singleThread.blurredImg();
            });
            blurThread.start();
        } else if ("M".equalsIgnoreCase(mode)) {
        
        }
    }
}
