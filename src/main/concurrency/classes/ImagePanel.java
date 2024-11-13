package src.main.concurrency.classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage img;

    public ImagePanel(BufferedImage img) {
        this.img = img;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);       
    }

    public void updateImage(BufferedImage img) {
        this.img = img;
        repaint();
    }
}