package src.main.concurrency.classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage img;

    public ImagePanel(BufferedImage img) {
        this.img = img;
    }

    // Method to draw the image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this); // Draw the image from the top-left corner
    }

    // Method to update the image on the panel
    public void updateImage(BufferedImage img) {
        this.img = img;
        repaint();
    }
}