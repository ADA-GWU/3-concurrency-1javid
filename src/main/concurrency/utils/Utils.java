package src.main.concurrency.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import src.main.concurrency.classes.ImagePanel;
import src.main.concurrency.classes.MultiThread;
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
        int newHeight = img.getHeight(), newWidth = img.getWidth();

        if (screenSize.height < newHeight) newHeight = screenSize.height;
        if (screenSize.width < newWidth) newWidth = screenSize.width;
        return new Dimension(newHeight, newWidth);
    }

    public static ImagePanel createImage(BufferedImage img, Dimension imgSize) {
        JFrame frame = new JFrame();
        ImagePanel imagePanel = new ImagePanel(img);
        frame.add(imagePanel);
        frame.setSize(imgSize.width, imgSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return imagePanel;
    }

    public static void processImage(String mode, BufferedImage img, ImagePanel imagePanel, int squareSize) {
        if ("S".equalsIgnoreCase(mode)) {

            SingleThread singleThread = new SingleThread(img, imagePanel, squareSize);

            long before = System.currentTimeMillis();
            singleThread.blurredImg();

            long after = System.currentTimeMillis();
            System.out.println("Time (ms): " + (after - before));

        } else if ("M".equalsIgnoreCase(mode)) {

            int cores = Runtime.getRuntime().availableProcessors();
            System.out.println("Your laptop has " + cores + " cores.");

            int threadSegment = img.getHeight() / cores;

            List<MultiThread> threads = new ArrayList<>();

            for (int i = 0; i < cores; i++) {
                int sHeight = i * threadSegment;
                int eHeight = (i + 1) * threadSegment;

                if (i + 1 == cores) eHeight = img.getHeight();

                threads.add(new MultiThread(img, sHeight, eHeight, imagePanel, squareSize));
            }

            long before = System.currentTimeMillis();

            for (MultiThread thread : threads) {
                thread.start();
            }

            for (MultiThread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }

            long after = System.currentTimeMillis();
            System.out.println("Time (ms): " + (after - before));
        }
    }
}
