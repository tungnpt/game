package game.player;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.island.FloatingIsland;
import input.KeyboardInput;
import physic.BoxCollider;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {
    public BufferedImage image;
    public boolean isFalling = true;
    public List<BulletPlayer> bulletPlayers = new ArrayList<>();
    Sword sword = new Sword();

    boolean equippedSword = true;
    boolean equippedGun = false;

    public boolean isAlive = true;

    private int currentBullet = 0;

    public int distance = 0;
    public int point = 0;

    public BoxCollider boxCollider;
    public FrameCounter frameCounter = new FrameCounter(7);

    float gravity = 0.5f;

    public Player() {
        super();
    }

    public Player(Vector2D position, BufferedImage image) {
        this.position.set(position);
        this.image = image;
        this.velocity = new Vector2D(5f, 5f);
        this.width = 81;
        this.height = 88;
        this.boxCollider = new BoxCollider(this.width, this.height);
    }

    public void run() {
        if (KeyboardInput.instance.isNum1){
            this.equippedSword = true;
            this.equippedGun = false;
        }
        if (KeyboardInput.instance.isNum2){
            this.equippedGun = true;
            this.equippedSword = false;
        }
        this.sword.position.set(new Vector2D(this.position.x + 60, this.position.y));
        if (KeyboardInput.instance.isSpace) {
            this.distance += 25;
            this.point = this.distance / 400;
            //System.out.println(this.point);

            if (this.equippedGun) {
                if (frameCounter.run()) {
                    this.addBullet();
                    this.frameCounter.reset();
                }
            }
            if (this.equippedSword) {
                this.sword.run();
            }
        }
        for (int i = 0; i < this.bulletPlayers.size(); i++) {
            this.bulletPlayers.get(i).run();
            if (bulletPlayers.get(i).position.x >= 1920) {
                bulletPlayers.get(i).isAlive = false;
            }
            System.out.println(this.bulletPlayers.size());
        }
        this.enemyHitPlayer();
        this.movingUpDonw();
        this.boxCollider.position.set(this.position);
    }

    public void render(Graphics graphics) {
        if (this.isAlive) {
            for (int i = 0; i < this.bulletPlayers.size(); i++) {
                this.bulletPlayers.get(i).render(graphics);
            }
            if (equippedSword) {
                this.sword.render(graphics);
            }
            graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, (int) this.width, (int) this.height, null);
        }
    }

    public void addBullet() {
        for (int i = 0; i < bulletPlayers.size(); i++) {
            if (bulletPlayers.get(i).isAlive == false) {
                bulletPlayers.get(i).isAlive = true;
                bulletPlayers.get(i).position.set(this.position);
                return;
            }
        }
        BulletPlayer bulletPlayer = new BulletPlayer();
        bulletPlayer.position.set(this.position);
        this.bulletPlayers.add(bulletPlayer);
        return;
    }

    public void enemyHitPlayer() {
        for (int j = 0; j < GameObjectManager.instance.list.size(); j++) {
            for (int k = 0; k < GameObjectManager.instance.list.get(j).enemies.size(); k++) {
                if (GameObjectManager.instance.list.get(j).enemies.get(k).isAlive == true && this.boxCollider.checkCollision(GameObjectManager.instance.list.get(j).enemies.get(k).boxCollider)) {
                    this.isAlive = false;
                }
            }
        }
    }

    public void movingUpDonw() {
        for (int i = 0; i < GameObjectManager.instance.list.size(); i++) {
            if (this.boxCollider.checkCollision(((FloatingIsland) GameObjectManager.instance.list.get(i)).boxCollider)) {
                this.isFalling = false;
            }
        }
        if (!isFalling) {
            this.velocity.y = 15.5f;
            this.velocity.y = -this.velocity.y;
            isFalling = true;
        } else {
            this.velocity.y += gravity;
        }
        this.position.y += this.velocity.y;
    }


}
