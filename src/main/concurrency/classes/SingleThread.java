package src.main.concurrency.classes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import src.main.concurrency.utils.ImageProcessingUtils;

public class SingleThread {
    private BufferedImage img;
    private int height;
    private int width;
    private ImagePanel imagePanel;
    private int squareSize;

    public SingleThread(BufferedImage img, ImagePanel imagePanel, int squareSize) {
        this.img = img;
        this.height = img.getHeight();
        this.width = img.getWidth();
        this.imagePanel = imagePanel;
        this.squareSize = squareSize;
    }

    public void blurredImg() {

        for (int i = 0; i < height; i += squareSize) {
            for (int j = 0; j < width; j += squareSize) {
                
                // Ensure boundaries fit within the image dimensions
                int heightBoundary = Math.min(squareSize, height - i);
                int widthBoundary = Math.min(squareSize, width - j);
        
                // Get a list of RGB values for the pixels in the current square
                List<List<Integer>> rgbList = ImageProcessingUtils.getRgbListBySquareSize(heightBoundary, widthBoundary, img, i, j);
        
                // Calculate the average color of the pixels in the square
                List<Integer> avgColor = ImageProcessingUtils.colorAverage(rgbList);
                Color newColor = new Color(avgColor.get(0), avgColor.get(1), avgColor.get(2));
        
                // Set the average color to all pixels in the current square
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