package game.graphics;

import game.util.LogSystem;
import game.util.audio.AudioLine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class Sprite {

    private BufferedImage img;
    private int width, height;

    public Sprite (String imagePath, int width, int height) {
        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.width = width;
        this.height = height;
        loadImage(imagePath);
    }

    private void loadImage (String imagePath) {
        LogSystem.log("Loading: " + imagePath + "...");

        try {
            img = ImageIO.read(Sprite.class.getClassLoader().getResource(imagePath));
        } catch (IOException e) {
            LogSystem.errorLog(imagePath + " could not be loaded");
            e.printStackTrace();
        }
    }

    public BufferedImage getImg() {
        return img;
    }

    public void render(Graphics2D g, int x, int y) {
        g.drawImage(img, x, y, width, height, null);
    }
}
