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

    FrameCounter frameCounter = new FrameCounter(110);
    FrameCounter timeDelayShoot = new FrameCounter(20);
    FrameCounter timeDelayDrop = new FrameCounter(4);
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
//        if(frameCounter.run()){
//            WeaponEnemy bullet = new WeaponEnemy();
//            bullet.position.set(this.position.x + this.width/2, this.position.y+ this.height);
//            bulletFlyingEnemies.add(bullet);
//            frameCounter.reset();
//        }

        //System.out.println(this.weaponEnemies.size());
        if (frameCounter.run()) {
            this.DropBomb();
            if (KeyboardInput.instance.isSpace) {
                this.position.x -= 25;
            }
            this.position.addUp(this.velocity);
        } else {
            this.shootBullet();
        }

        this.boxCollider.position.set(this.position);
        this.weaponEnemies.forEach(bulletFlyingEnemy -> bulletFlyingEnemy.run());
    }

    public void shootBullet() {
        if (this.timeDelayShoot.run()) {
            BulletEnemy bulletEnemy = new BulletEnemy();
            bulletEnemy.position.set(this.position);
//            Vector2D velo = GameCanvas.player.position
//                    .subtract(this.position)
//                    .normalize()
//                    .multiply(15f);
//            bulletEnemy.velocity.set(velo);
            this.weaponEnemies.add(bulletEnemy);
            this.timeDelayShoot.reset();
        }
    }

    public void DropBomb() {
        if (this.timeDelayDrop.run()) {
            BombEnemy bombEnemy = new BombEnemy();
            bombEnemy.position.set(this.position);
            this.weaponEnemies.add(bombEnemy);
            this.timeDelayDrop.reset();
        }
    }


    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, (int) this.width, (int) this.height, null);
        this.weaponEnemies.forEach(bulletFlyingEnemy -> bulletFlyingEnemy.render(graphics));
    }
}
