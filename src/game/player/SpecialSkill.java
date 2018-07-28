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

    //public boolean isActived = false;


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
        this.animation = new Animation(2, image[0], image[1], image[2], image[3], image[4], image[5]);
    }

    public void run() {
        if (GameCanvas.player.energy >=3) {
            super.run();
            this.isAlive = true;
            this.boxCollider = new BoxCollider(this.width, this.height);
            this.isAlive = true;
            animation.runAnimation();
            this.boxCollider.position.set(this.position.x, this.position.y);
        }
    }

    public void render(Graphics graphics) {
        if (GameCanvas.player.energy >=3) {
            animation.drawAnimation(graphics, this.position.x, this.position.y, 560, 560, 0);
        }
        if (!KeyboardInput.instance.isEnter){
            GameCanvas.player.energy = 0;
        }
    }
}


