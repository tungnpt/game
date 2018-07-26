package game.enemy;

import base.*;
import gameplay.GameCanvas;
import input.KeyboardInput;
import physic.BoxCollider;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlyingEnemy extends GameObject {
    public BufferedImage image = LoadImage.loadImage("resources/enemy.png");
    public BoxCollider boxCollider;
    public List<WeaponEnemy> weaponEnemies = new ArrayList<>();

    FrameCounter frameCounter = new FrameCounter(250);
    FrameCounter timeDelayShoot = new FrameCounter(30);
    FrameCounter timeDelayDrop = new FrameCounter(10);
    FrameCounter timeComeBack = new FrameCounter(3000);
    Random random = new Random();

    public FlyingEnemy() {
        super();
        this.position = new Vector2D(1920, 10);
        this.velocity = new Vector2D(-12f, 0);
        this.width = 81;
        this.height = 88;
        this.boxCollider = new BoxCollider(this.width, this.height);
    }

    public void run() {
        //System.out.println(this.weaponEnemies.size());
        if (timeComeBack.run()) {
            if (frameCounter.run()) {
                this.velocity.set(new Vector2D(-12f, 0));
                this.DropBomb();
                if (KeyboardInput.instance.isSpace) {
                    this.position.x -= 25;
                }
                this.position.addUp(this.velocity);
            } else {
                this.velocity.set(new Vector2D(-1f, 0));
                this.shootBullet();
                this.position.addUp(this.velocity);
            }
            this.boxCollider.position.set(this.position);
            if (this.position.x + this.width <=0){
                this.timeComeBack.reset();
                this.setupAgian();
            }
        }
        this.weaponEnemies.forEach(bulletFlyingEnemy -> bulletFlyingEnemy.run());
    }

    public void setupAgian(){
        this.position.set(new Vector2D(1920, 10));
        this.weaponEnemies.clear();
        this.frameCounter.reset();
        this.timeDelayDrop.reset();
        this.timeDelayDrop.reset();
    }

    public void shootBullet() {
        if (this.timeDelayShoot.run()) {
            BulletEnemy bulletEnemy = new BulletEnemy();
            bulletEnemy.position.set(this.position.x + this.width/2, this.position.y + this.height);
            this.weaponEnemies.add(bulletEnemy);
            this.timeDelayShoot.reset();
        }
    }

    public void DropBomb() {
        if (this.timeDelayDrop.run()) {
            BombEnemy bombEnemy = new BombEnemy();
            bombEnemy.position.set(this.position.x + this.width/2, this.position.y + this.height);
            this.weaponEnemies.add(bombEnemy);
            this.timeDelayDrop.reset();
        }
    }


    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, (int) this.width, (int) this.height, null);
        this.weaponEnemies.forEach(bulletFlyingEnemy -> bulletFlyingEnemy.render(graphics));
    }
}
