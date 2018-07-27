package game.player;

import animation.Animation;
import base.*;
import gameplay.GameCanvas;
import input.KeyboardInput;
import physic.BoxCollider;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sword extends GameObject {
    public BufferedImage[] image = new BufferedImage[13];
    public BoxCollider boxCollider;
    public Animation animation;

    int offset = 0;

    public Sword() {
        super();
        this.position = new Vector2D();
        String stringPath;
        for (int i = 1; i <= 13; i++) {
            stringPath = "resources/sword/s" + Integer.toString(i) + ".png";
            this.image[i - 1] = LoadImage.loadImage(stringPath);
        }
        this.velocity = new Vector2D();
        this.width = 81;
        this.height = 88;

        this.boxCollider = new BoxCollider(this.width, this.height);

        this.animation = new Animation(1, image[0], image[3], image[6], image[9], image[12]);
    }

    public void run() {
        super.run();
        animation.runAnimation();
        this.boxCollider.position.set(this.position.x, this.position.y);
        this.hitEnemy();
    }

    public void render(Graphics graphics) {
        if (KeyboardInput.instance.isSpace) {
            this.setOffset();
            animation.drawAnimation(graphics, this.position.x, this.position.y, offset);
        }
        if (!KeyboardInput.instance.isSpace) {
            animation.reset();
        }
    }

    public void setOffset() {
        if (this.animation.currentImg == image[0]) {
            offset = 0;
        } else if (this.animation.currentImg == image[1]) {
            offset = 1;
        } else if (this.animation.currentImg == image[2]) {
            offset = 20;
        } else if (this.animation.currentImg == image[3]) {
            offset = 30;
        } else if (this.animation.currentImg == image[4]) {
            offset = 40;
        } else if (this.animation.currentImg == image[5]) {
            offset = 50;
        } else if (this.animation.currentImg == image[6]) {
            offset = 60;
        } else if (this.animation.currentImg == image[7]) {
            offset = 70;
        } else if (this.animation.currentImg == image[8]) {
            offset = 80;
        } else if (this.animation.currentImg == image[9]) {
            offset = 90;
        } else if (this.animation.currentImg == image[10]) {
            offset = 100;
        } else if (this.animation.currentImg == image[11]) {
            offset = 110;
        } else if (this.animation.currentImg == image[12]) {
            offset = 120;
        }
    }

    public void hitEnemy() {
        for (int i = 0; i < GameObjectManager.instance.list.size(); i++) {
            for (int j = 0; j < GameObjectManager.instance.list.get(i).enemies.size(); j++) {
                if (this.boxCollider.checkCollision(GameObjectManager.instance.list.get(i).enemies.get(j).boxCollider) && GameObjectManager.instance.list.get(i).enemies.get(j).isAlive) {
                    GameObjectManager.instance.list.get(i).enemies.get(j).isAlive = false;
                    GameCanvas.player.energy +=1;
                }
            }
        }
    }
}
