package game.player;

import animation.Animation;
import base.GameObject;
import base.LoadImage;
import base.Vector2D;
import gameplay.GameCanvas;
import input.KeyboardInput;
import physic.BoxCollider;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpecialSkill extends GameObject {
    public BufferedImage[] image = new BufferedImage[6];
    public BoxCollider boxCollider;

    public Animation animation;


    public SpecialSkill() {
        super();
        this.position = new Vector2D();
        String stringPath;
        for (int i = 1; i <= 6; i++) {
            stringPath = "resources/fire/fire" + Integer.toString(i) + ".png";
            this.image[i - 1] = LoadImage.loadImage(stringPath);
        }
        this.velocity = new Vector2D();
        this.width = 560;
        this.height = 560;
        this.isAlive = false;
        this.boxCollider = new BoxCollider(this.width, this.height);
        this.animation = new Animation(1, image[0], image[1], image[2], image[3], image[4], image[5], image[5], image[5]);
    }

    public void run(){
        if (KeyboardInput.instance.isEnter) {
            super.run();
            this.isAlive = true;
            animation.runAnimation();
            GameCanvas.player.energy = 0;
        }
        this.boxCollider.position.set(this.position.x, this.position.y);
        if (!KeyboardInput.instance.isEnter) {
            this.isAlive = false;
        }
    }

    public void render(Graphics graphics) {
        if (KeyboardInput.instance.isEnter) {
            animation.drawAnimation(graphics, this.position.x, this.position.y, 560,560, 0);
        }
        if (!KeyboardInput.instance.isEnter) {
            animation.reset();
        }
    }
}


