package game.backgound;

import base.GameObject;
import base.LoadImage;
import renderer.ImageRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background extends GameObject {
    public BufferedImage image = LoadImage.loadImage("resources/background.jpg");

    public Background() {
        this.renderer = new ImageRenderer("resources/background.jpg", 1920, 1200);
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, 0, 0, 1920, 1200, null);
    }
}
