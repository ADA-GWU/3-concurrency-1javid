package src.main.concurrency.utils;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import src.main.concurrency.classes.ImagePanel;

public class ImagePanelUtils {

    public static ImagePanel createImagePanel(BufferedImage img, Dimension imgSize) {
        JFrame frame = new JFrame();
        ImagePanel imagePanel = new ImagePanel(img);
        frame.add(imagePanel);
        frame.setSize(imgSize.width, imgSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return imagePanel;
    }
}