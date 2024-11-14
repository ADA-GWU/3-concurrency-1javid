package src.main.concurrency.classes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import src.main.concurrency.utils.ImageProcessingUtils;

public class MultiThread extends Thread {
    private BufferedImage img;
    private int sHeight;
    private int eHeight;
    private int width;
    private ImagePanel imagePanel;
    private int squareSize;

    public MultiThread(BufferedImage img, int sHeight, int eHeight, ImagePanel imagePanel, int squareSize) {
        super();

        this.img = img;
        this.sHeight = sHeight;
        this.eHeight = eHeight;
        this.width = img.getWidth();
        this.imagePanel = imagePanel;
        this.squareSize = squareSize;
    }

    @Override
    public void run() {
        
        for (int i = sHeight; i < eHeight; i += squareSize) {
            for (int j = 0; j < width; j += squareSize) {
                
                int heightBoundary = Math.min(squareSize, eHeight - i);
                int widthBoundary = Math.min(squareSize, width - j);
        
                List<List<Integer>> rgbList = ImageProcessingUtils.getRgbListBySquareSize(heightBoundary, widthBoundary, img, i, j);
        
                List<Integer> avgColor = ImageProcessingUtils.colorAverage(rgbList);
                Color newColor = new Color(avgColor.get(0), avgColor.get(1), avgColor.get(2));
        
                ImageProcessingUtils.setNewRgbToImg(heightBoundary, widthBoundary, img, newColor, imagePanel, i, j);
        
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}