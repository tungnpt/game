package game.enemy;

import base.GameObject;
import base.GameObjectManager;
import base.LoadImage;
import base.Vector2D;
import gameplay.GameCanvas;
import input.KeyboardInput;
import physic.BoxCollider;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeaponEnemy extends GameObject {
    public BufferedImage image = LoadImage.loadImage("resources/enemy.png");
    public BoxCollider boxCollider;
    public WeaponEnemy(){
        this.position= new Vector2D();
        this.velocity= new Vector2D();
        this.width = 10;
        this.height = 15;
        this.boxCollider = new BoxCollider(this.width, this.height);
    }

    public void run() {
        if (KeyboardInput.instance.isSpace){
            this.position.x -=25;
        }
        for (int j = 0; j<GameObjectManager.instance.list.size(); j++){
            if(this.boxCollider.checkCollision((GameObjectManager.instance.list.get(j)).boxCollider)){
                this.isAlive = false;
            }
        }
        if (this.isAlive) {
            this.position.addUp(this.velocity);
        }
        this.boxCollider.position.set(this.position);
        if (this.position.y >1200 || (this.boxCollider.checkCollision(GameCanvas.player.specialSkill.boxCollider) && GameCanvas.player.specialSkill.isAlive)){
            this.isAlive = false;
        }
        if (this.boxCollider.checkCollision(GameCanvas.player.boxCollider)){
            GameCanvas.player.isAlive = false;
        }
    }

    public void render(Graphics graphics) {
        if (this.isAlive) {
            graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, (int) this.width, (int) this.height, null);
        }
    }
}
