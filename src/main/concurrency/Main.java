package src.main.concurrency;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

class Main {
    
    public static void main(String[] args) {
        
        try{
            File imgPath = new File("./img/image.png");
            BufferedImage img = ImageIO.read(imgPath);
            SingleThread singleThread = new SingleThread(img);
            
            singleThread.blurredImg();


        } catch (Exception e) {
            e.setStackTrace(null);
        }
        
    }
}