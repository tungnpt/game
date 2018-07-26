package scene;

import base.GameObjectManager;
import base.LoadImage;
import game.backgound.Background;
import gameplay.GameCanvas;

public class GameOverScene implements Scene {
    @Override
    public void init() {
        GameCanvas.background = new Background();
        GameCanvas.background.image = LoadImage.loadImage("resources/gameover.png");
    }

    @Override
    public void deinit() {
        GameCanvas.background = null;
    }
}
