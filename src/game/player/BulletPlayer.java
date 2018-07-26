package game.player;

import base.GameObject;
import base.GameObjectManager;
import base.LoadImage;
import base.Vector2D;
import gameplay.GameCanvas;
import physic.BoxCollider;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BulletPlayer extends GameObject {
    public BufferedImage image = LoadImage.loadImage("resources/player.png");
    public BoxCollider boxCollider;

    public BulletPlayer(){
        this.position= new Vector2D();
        this.velocity= new Vector2D(25,0f);
        this.width = 10;
        this.height = 15;
        this.boxCollider = new BoxCollider(this.width, this.height);
    }

    public void run() {
        if (this.isAlive) {
            this.position.addUp(this.velocity);
        }
        this.boxCollider.position.set(this.position);
        this.hitEnemy();
    }

    public void hitEnemy(){
        for (int j = 0; j<GameObjectManager.instance.list.size(); j++){
            for (int k=0; k<GameObjectManager.instance.list.get(j).enemies.size(); k++){
                if (this.isAlive == true && GameObjectManager.instance.list.get(j).enemies.get(k).isAlive== true &&this.boxCollider.checkCollision(GameObjectManager.instance.list.get(j).enemies.get(k).boxCollider)){
                    this.isAlive = false;
                    GameObjectManager.instance.list.get(j).enemies.get(k).isAlive = false;
                    GameCanvas.player.energy +=1;
                }
            }
        }
    }

    public void render(Graphics graphics) {
        if (this.isAlive) {
            graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, (int) this.width, (int) this.height, null);
        }
    }
}
