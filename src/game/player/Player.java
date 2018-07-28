package game.player;

import base.*;
import game.island.FloatingIsland;
import input.KeyboardInput;
import physic.BoxCollider;
import renderer.ImageRenderer;
import renderer.Renderer;
import renderer.TextRenderer;
import scene.GameOverScene;
import scene.SceneManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {
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
    public Renderer textRenderer;

    public BoxCollider boxCollider;
    public FrameCounter frameCounter = new FrameCounter(7);

    float gravity = 0.5f;

    public Player() {
        super();
        this.position.set(new Vector2D(300, 400));
        this.velocity = new Vector2D(5f, 5f);
        this.width = 81;
        this.height = 88;
        this.renderer = new ImageRenderer("resources/player.png", this.width,this.height);
        this.attributes.add(new PlayerMove());
        this.attributes.add(new PlayerAttack());
        this.boxCollider = new BoxCollider(this.width, this.height);
    }

    public void run() {
        super.run();
        System.out.println(this.energy);
        this.specialSkill.boxCollider.position.set(this.specialSkill.position);
        this.boxCollider.position.set(this.position);
        if (!this.isAlive){
            SceneManager.instance.changeScene(new GameOverScene());
        }
    }

    public void render(Graphics graphics) {
        this.textRenderer = new TextRenderer(
                Integer.toString(this.point),
                Color.WHITE,
                "French Script MT",
                60
        );
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
            super.render(graphics);
        }
        this.skillImage = LoadImage.loadImage("resources/energy/energy" + Integer.toString(this.energy/1) + ".png");
        graphics.drawImage(this.skillImage, 0, 880, 120, 120, null);
        textRenderer.render(graphics, new Vector2D(960, 100));
    }
}
