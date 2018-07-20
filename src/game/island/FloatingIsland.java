package game.island;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.enemy.Enemy;
import input.KeyboardInput;
import physic.BoxCollider;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FloatingIsland extends GameObject {
    public BufferedImage image;

    public List<Enemy> enemies = new ArrayList<>();
    Random random = new Random();
    public int enemyQuantity = 0;
    public BoxCollider boxCollider;

    public FloatingIsland() {
        super();
        this.position = new Vector2D();
        this.velocity = new Vector2D(25f, 0);
        this.enemies = new ArrayList<>();
    }

    public FloatingIsland(Vector2D position, BufferedImage image, int width, int height) {
        this.position.set(position);
        this.image = image;
        this.width = width;
        this.height = height;
        this.velocity = new Vector2D(25f, 0);
        this.setupEnemies();
        this.boxCollider = new BoxCollider(this.width -10, 1);
    }

    public void run() {
        this.enemies.forEach(enemy -> {
//            if(physic.OnIsland.checkOnIsland(enemy,this)){
//                enemy.isFalling = false;
//            }
            if(enemy.boxCollider.checkCollision(this.boxCollider)){
                enemy.isFalling = false;
            }
        });
        if (KeyboardInput.instance.isSpace) {
            this.backToScreen();
            this.position.subtractBy(this.velocity);
        }
        this.boxCollider = new BoxCollider(this.width -10, 1);
        this.boxCollider.position.set(this.position.x + 10,  this.position.y);
        this.enemies.forEach(enemy -> enemy.run());
    }

    public void setupEnemies() {
        this.enemies.clear();
        if (this.width >= 700 & this.width<=800){
            enemyQuantity = random.nextInt(5);
        }
        else if (this.width >= 800 & this.width<=900){
            enemyQuantity = random.nextInt(6);
        }
        else if (this.width >= 900 & this.width<=1000){
            enemyQuantity = random.nextInt(7);
        }
        else if (this.width >= 1000 & this.width<=1100){
            enemyQuantity = random.nextInt(8);
        }
        else if (this.width >= 1100 & this.width<=1200){
            enemyQuantity = random.nextInt(9);
        }
        int amountDistance = 80*(enemyQuantity + enemyQuantity -1);
        //enemies.add(new game.enemy.Enemy(new base.Vector2D(this.position.x + this.width/2 - amountDistance/2, this.position.y - random.nextInt(100) - 100)));
        for (int i=0;  i<=enemyQuantity-1; i++){
            enemies.add(new Enemy(new Vector2D((float) (this.position.x + this.width/2 - amountDistance/2 + 80 * 2 * i),this.position.y - random.nextInt(100) - 100)));
        }
    }

    public void backToScreen() {
        if (this.position.x + this.width <= 0) {
            int num = random.nextInt(200) + 200;
            this.position.x = GameObjectManager.instance.biggestPositionX + num;
            this.position.y = random.nextInt(200) + 500;
            this.width = random.nextInt(500) +  700;
            this.height = random.nextInt(100)+300;
            String temp = Integer.toString(random.nextInt(4)+1);
            String path = "resources/Island" + temp + ".png";
            this.image = loadImage(path);
            this.enemies.clear();
            this.setupEnemies();
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, this.width, this.height, null);
        this.enemies.forEach(enemy -> enemy.render(graphics));
    }
}
