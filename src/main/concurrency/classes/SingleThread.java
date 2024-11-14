package src.main.concurrency.classes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import src.main.concurrency.utils.Utils;

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
                
                int heightBoundary = Math.min(squareSize, height - i);
                int widthBoundary = Math.min(squareSize, width - j);
        
                List<List<Integer>> rgbList = new ArrayList<>();
                for (int k = 0; k < heightBoundary; k++) {
                    for (int l = 0; l < widthBoundary; l++) {
                        int rgb = img.getRGB(j + l, i + k);
                        Color color = new Color(rgb);
                        rgbList.add(List.of(color.getRed(), color.getGreen(), color.getBlue()));
                    }
                }
        
                List<Integer> avgColor = Utils.colorAverage(rgbList);
                Color newColor = new Color(avgColor.get(0), avgColor.get(1), avgColor.get(2));
        
                for (int k = 0; k < heightBoundary; k++) {
                    for (int l = 0; l < widthBoundary; l++) {
                        img.setRGB(j + l, i + k, newColor.getRGB());
                    }
                }
        
                imagePanel.updateImage(img);
        
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}