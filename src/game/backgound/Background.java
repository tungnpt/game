package game.backgound;

import base.LoadImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {
    public BufferedImage image = LoadImage.loadImage("resources/background.jpg");

    public Background(BufferedImage image) {
        this.image = image;
    }

    public Background() {
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, 0, 0, 1920, 1200, null);
    }
}
