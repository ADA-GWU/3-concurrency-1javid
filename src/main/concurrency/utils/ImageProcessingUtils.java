package src.main.concurrency.utils;

import java.awt.Color;
import java.util.List;

import src.main.concurrency.classes.ImagePanel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageProcessingUtils {

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

    public static List<List<Integer>> getRgbListBySquareSize(int height, int width, BufferedImage img, int i, int j) {
        List<List<Integer>> rgbList = new ArrayList<>();

        for (int k = 0; k < height; k++) {
            for (int l = 0; l < width; l++) {
                int rgb = img.getRGB(j + l, i + k);
                Color color = new Color(rgb);
                rgbList.add(List.of(color.getRed(), color.getGreen(), color.getBlue()));
            }
        }

        return rgbList;
    }

    public static void setNewRgbToImg(int heightBoundary, int widthBoundary, BufferedImage img, Color newColor, ImagePanel imagePanel, int i, int j) {
        for (int k = 0; k < heightBoundary; k++) {
            for (int l = 0; l < widthBoundary; l++) {
                img.setRGB(j + l, i + k, newColor.getRGB());
            }
        }

        imagePanel.updateImage(img);
    }

}