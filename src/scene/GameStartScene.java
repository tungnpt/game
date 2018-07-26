package scene;

import base.LoadImage;
import game.backgound.Background;
import gameplay.GameCanvas;

public class GameStartScene implements Scene {
    @Override
    public void init() {
        GameCanvas.background = new Background();
        GameCanvas.background.image = LoadImage.loadImage("resources/start.png");
    }

    @Override
    public void deinit() {
        GameCanvas.background = null;
    }
}
