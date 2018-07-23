package game.enemy;

import base.*;
import game.island.FloatingIsland;
import physic.BoxCollider;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlyingEnemy extends GameObject {
    public BufferedImage image = LoadImage.loadImage("resources/enemy.png");
    public BoxCollider boxCollider;
    public List<BulletFlyingEnemy>bulletFlyingEnemies = new ArrayList<>();

    FrameCounter frameCounter = new FrameCounter(4);
    Random random = new Random();

    public FlyingEnemy() {
        super();
        this.position = new Vector2D(1920, 10);
        this.velocity = new Vector2D(-12f, 0);
        this.width = 81;
        this.height = 88;
        this.boxCollider = new BoxCollider(this.width, this.height);
    }

    public FlyingEnemy(Vector2D position) {
        this.position.set(position);
        this.velocity = new Vector2D(-25f, 0);
        this.width = 80;
        this.height = 60;

        this.boxCollider = new BoxCollider(this.width, this.height);
    }

    public void run() {
        //this.bulletFlyingEnemies.clear();
        if(frameCounter.run()){
            BulletFlyingEnemy bullet = new BulletFlyingEnemy();
            bullet.position.set(this.position.x + this.width/2, this.position.y+ this.height);
            //bullet.velocity.set(new base.Vector2D(0, 1f));
            bulletFlyingEnemies.add(bullet);
            frameCounter.reset();
        }
        for (int i=0; i<this.bulletFlyingEnemies.size(); i++){
            for (int j=0; j<GameObjectManager.instance.list.size(); j++){
                if(this.bulletFlyingEnemies.get(i).boxCollider.checkCollision(((FloatingIsland) GameObjectManager.instance.list.get(j)).boxCollider)){
                    this.bulletFlyingEnemies.get(i).isAlive = false;
                }
            }
        }
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position);

        this.bulletFlyingEnemies.forEach(bulletFlyingEnemy -> bulletFlyingEnemy.run());
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, (int) this.width, (int) this.height, null);
        this.bulletFlyingEnemies.forEach(bulletFlyingEnemy -> bulletFlyingEnemy.render(graphics));
    }
}
