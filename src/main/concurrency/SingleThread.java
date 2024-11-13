package src.main.concurrency;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

class SingleThread {
    private BufferedImage img;
    private int height;
    private int width;

    SingleThread(BufferedImage img) {
        this.img = img;
        this.height = img.getHeight();
        this.width = img.getWidth();
    }

    public void blurredImg() {

        for (int i = 0; i < height; i += 20) {
            for (int j = 0; j < width; j += 20) {

                int heightBoundary = Math.min(20, width - i);
                int witdhBoundary = Math.min(20, height - j);
                
                List<List<Integer>> rgbList = new ArrayList<>();
                for (int k = i; k < heightBoundary + i; k++) {
                    for (int l = j; l < witdhBoundary + j; l++) {

                        int rgb = img.getRGB(k, l);
                        Color color = new Color(rgb);

                        int red = color.getRed();
                        int green = color.getGreen();
                        int blue = color.getBlue();

                        rgbList.add(List.of(red, green, blue));
                    }
                }

                int newRgb = Utils.colorAverage(rgbList);

                for (int k = i; k < heightBoundary + i; k++) {
                    for (int l = j; l < witdhBoundary + j; l++) {
                        img.setRGB(k, l, newRgb);
                    }
                }

            }
        }
    }
}