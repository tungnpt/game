package game.enemy;

import base.*;
import gameplay.GameCanvas;
import input.KeyboardInput;
import physic.BoxCollider;
import renderer.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlyingEnemy extends GameObject {
    public BoxCollider boxCollider;
    public List<WeaponEnemy> weaponEnemies = new ArrayList<>();

    FrameCounter frameCounter = new FrameCounter(250);
    FrameCounter timeDelayShoot = new FrameCounter(30);
    FrameCounter timeDelayDrop = new FrameCounter(10);
    FrameCounter timeComeBack = new FrameCounter(2500);
    Random random = new Random();

    public FlyingEnemy() {
        super();
        this.position = new Vector2D(1920, 10);
        this.velocity = new Vector2D(-12f, 0);
        this.width = 81;
        this.height = 88;
        this.renderer = new ImageRenderer("resources/enemy.png", this.width, this.height);
        this.attributes.add(new FlyingEnemyAttack());
        this.boxCollider = new BoxCollider(this.width, this.height);
    }

    public void run() {
        super.run();
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
        super.render(graphics);
        this.weaponEnemies.forEach(bulletFlyingEnemy -> bulletFlyingEnemy.render(graphics));
    }
}
