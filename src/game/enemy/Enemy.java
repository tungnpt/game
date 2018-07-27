package game.enemy;

import base.GameObject;
import base.LoadImage;
import base.Vector2D;
import gameplay.GameCanvas;
import input.KeyboardInput;
import physic.BoxCollider;
import renderer.ImageRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy extends GameObject {
    //public BufferedImage image = LoadImage.loadImage("resources/enemy.png");
    public boolean isFalling = true;
    public BoxCollider boxCollider;

    float gravity = 0.5f;

    public Enemy() {
        super();
        this.velocity = new Vector2D(25f, 5f);
        this.width = 80;
        this.height = 60;
        this.renderer = new ImageRenderer("resources/enemy.png", this.width, this.height);
    }

    public Enemy(Vector2D position) {
        this.position.set(position);
        this.velocity = new Vector2D(25f, 5f);
        this.width = 81;
        this.height = 88;
        this.renderer = new ImageRenderer("resources/enemy.png", this.width, this.height);
        this.boxCollider = new BoxCollider(this.width, this.height);
    }

    public void run() {
        if (KeyboardInput.instance.isSpace) {
            this.position.x -= this.velocity.x;
        }
        if (isFalling == false) {
            this.velocity.y = -this.velocity.y;
            isFalling = true;
        } else {
            this.velocity.y += gravity;
        }
        this.position.y += this.velocity.y;
        this.boxCollider.position.set(this.position);
        if (this.isAlive && this.boxCollider.checkCollision(GameCanvas.player.boxCollider)){
            GameCanvas.player.isAlive= false;
        }
        if(this.boxCollider.checkCollision(GameCanvas.player.specialSkill.boxCollider) && GameCanvas.player.specialSkill.isAlive == true){
            this.isAlive = false;
        }
    }

    public void render(Graphics graphics) {
        if (this.isAlive) {
            super.render(graphics);
        }
    }
}
