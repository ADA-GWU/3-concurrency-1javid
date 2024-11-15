package src.main.concurrency.utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import src.main.concurrency.classes.ImagePanel;
import src.main.concurrency.classes.MultiThread;
import src.main.concurrency.classes.SingleThread;

public class ThreadingUtils {

    public static void processImageSingleThread(BufferedImage img, ImagePanel imagePanel, int squareSize) {
        SingleThread singleThread = new SingleThread(img, imagePanel, squareSize);

        long before = System.currentTimeMillis();
        singleThread.blurredImg(); 
        long after = System.currentTimeMillis(); 

        System.out.println("Single-threaded Time (ms): " + (after - before)); 
    }

    public static void processImageMultiThread(BufferedImage img, ImagePanel imagePanel, int squareSize) {
        int cores = Runtime.getRuntime().availableProcessors(); 
        System.out.println("Your laptop has " + cores + " cores.");

        int threadSegment = img.getHeight() / cores; 
        List<MultiThread> threads = new ArrayList<>(); 

        // Loop to assign a thread (core) for each segment of the image
        for (int i = 0; i < cores; i++) {
            int sHeight = i * threadSegment; 
            int eHeight = (i + 1) * threadSegment; 
            if (i + 1 == cores) eHeight = img.getHeight(); // Adjust last segment to cover remaining height

            threads.add(new MultiThread(img, sHeight, eHeight, imagePanel, squareSize));
        }

        long before = System.currentTimeMillis(); 

        for (MultiThread thread : threads) {
            thread.start();
        }

        // Wait for all threads to finish execution
        for (MultiThread thread : threads) {
            try {
                thread.join(); // Join thread to ensure all are complete
            } catch (InterruptedException ex) {
                System.out.println(ex); 
            }
        }

        long after = System.currentTimeMillis(); 
        System.out.println("Multi-threaded Time (ms): " + (after - before)); 
    }
}
