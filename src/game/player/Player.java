package game.player;

import base.*;
import game.island.FloatingIsland;
import input.KeyboardInput;
import physic.BoxCollider;
import scene.GameOverScene;
import scene.SceneManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {
    public BufferedImage image = LoadImage.loadImage("resources/player.png");
    public boolean isFalling = true;
    public List<BulletPlayer> bulletPlayers = new ArrayList<>();
    public Sword sword = new Sword();
    public SpecialSkill specialSkill = new SpecialSkill();
    public BufferedImage skillImage = LoadImage.loadImage("resources/energy/energy0.png");

    public int energy = 0;

    boolean equippedSword = true;
    boolean equippedGun = false;

    public int distance = 0;
    public int point = 0;

    public BoxCollider boxCollider;
    public FrameCounter frameCounter = new FrameCounter(7);

    float gravity = 0.5f;

    public Player() {
        super();
        this.position.set(new Vector2D(300, 400));
        this.velocity = new Vector2D(5f, 5f);
        this.width = 81;
        this.height = 88;
        this.attributes.add(new PlayerMove());
        this.attributes.add(new PlayerAttack());
        this.boxCollider = new BoxCollider(this.width, this.height);
    }

    public void run() {
        super.run();
        //Cần Edit lại cho đỡ bá
        //System.out.println(this.bulletPlayers.size());
        this.specialSkill.boxCollider.position.set(this.specialSkill.position);
        this.boxCollider.position.set(this.position);
        if (!this.isAlive){
            SceneManager.instance.changeScene(new GameOverScene());
        }
    }

    public void render(Graphics graphics) {
        if (this.isAlive) {
            if (KeyboardInput.instance.isEnter) {
                this.specialSkill.render(graphics);
            }
            for (int i = 0; i < this.bulletPlayers.size(); i++) {
                this.bulletPlayers.get(i).render(graphics);
            }
            if (equippedSword) {
                this.sword.render(graphics);
            }
            graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, (int) this.width, (int) this.height, null);
        }
        this.skillImage = LoadImage.loadImage("resources/energy/energy" + Integer.toString(this.energy/10) + ".png");
        graphics.drawImage(this.skillImage, 0, 880, 120, 120, null);
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
}
