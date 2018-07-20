import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.island.FloatingIsland;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CreateIsland extends GameObject {
    private FrameCounter frameCounter;
    private Random random;
    private int previousIslandPositionX = 0;

    FloatingIsland island;

    public CreateIsland() {
        this.frameCounter = new FrameCounter(30);
        this.random = new Random();
    }

    public void run() {
        if(this.frameCounter.run()) {
            island = new FloatingIsland(new Vector2D(previousIslandPositionX + random.nextInt(100) + 100,
                    random.nextInt(100) + 500),
                    this.loadImage("resources/Island1.png"),
                    random.nextInt(100) + 500,
                    400
            );
            previousIslandPositionX = (int) island.position.x + island.width;
            GameObjectManager.instance.add(island);
            this.frameCounter.reset();
        }
    }
    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}
