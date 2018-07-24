package scene;

import base.GameObjectManager;
import game.backgound.Background;
import game.enemy.FlyingEnemy;
import game.player.Player;
import gameplay.GameCanvas;

import javax.sound.sampled.Clip;

public class GamePlayScene implements Scene {

    @Override
    public void init() {
        this.setupCharacter();
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.list.clear();
    }

    private void setupCharacter() {
        GameCanvas.background = new Background();
        GameCanvas.player = new Player();
        GameCanvas.flyingEnemy = new FlyingEnemy();
    }
}
