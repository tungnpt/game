package base;

import game.island.FloatingIsland;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameObjectManager {

    public static GameObjectManager instance = new GameObjectManager();

    public ArrayList<FloatingIsland> list = new ArrayList<>();
    private ArrayList<GameObject> tempList = new ArrayList<>();

    public int biggestPositionX = 0;

    private Random random;

    private GameObjectManager() {
        this.list = new ArrayList<>();
        FloatingIsland floatingIsland = new FloatingIsland(new Vector2D(200,700),
                LoadImage.loadImage("resources/Island1.png"),
                700,
                300);
        floatingIsland.enemies.clear();
        list.add(floatingIsland);
        floatingIsland = new FloatingIsland(new Vector2D(1100,700),
                LoadImage.loadImage("resources/Island2.png"),
                800,
                300);
        list.add(floatingIsland);
        floatingIsland = new FloatingIsland(new Vector2D(2100,700),
                LoadImage.loadImage("resources/Island3.png"),
                1200,
                300);
        list.add(floatingIsland);
        floatingIsland = new FloatingIsland(new Vector2D(3500,700),
                LoadImage.loadImage("resources/Island4.png"),
                1200,
                300);
        list.add(floatingIsland);
    }

    public void add(FloatingIsland gameObject) {
        this.list.add(gameObject);
    }
    public void runAll() {
        //if (input.KeyboardInput.instance.isSpace) {
            this.biggestPositionX = (int) this.list.get(0).position.x + this.list.get(0).width;
            for (int i=0; i<list.size(); i++){
                if ((int)this.list.get(i).position.x + this.list.get(i).width > this.biggestPositionX){
                    this.biggestPositionX = (int)this.list.get(i).position.x + this.list.get(i).width;
                }
            }
            this.list
                    .stream()
                    .forEach(gameObject -> gameObject.run());
    }

    public void renderAll(Graphics graphics) {
        this.list
                .stream()
                .forEach(gameObject -> gameObject.render(graphics));
    }
    public void reset(){
        GameObjectManager.instance = new GameObjectManager();
    }
}
