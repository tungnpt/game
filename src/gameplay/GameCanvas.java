package gameplay;

import base.GameObjectManager;
import base.LoadImage;
import base.Vector2D;
import game.backgound.Background;
import game.island.FloatingIsland;
import game.enemy.FlyingEnemy;
import game.player.Player;
import game.player.SpecialSkill;
import game.player.Sword;
import scene.GamePlayScene;
import scene.SceneManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameCanvas extends JPanel {
    BufferedImage backBuffered;
    Graphics graphics;

    public static Background background = new Background();
    public static Player player = new Player();
    public static FlyingEnemy flyingEnemy = new FlyingEnemy();


    public GameCanvas() {
        this.setSize(1920, 1200);

        this.setupBackBuffered();

        this.setVisible(true);

        SceneManager.instance.changeScene(new GamePlayScene());
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(1920, 1200, BufferedImage.TYPE_3BYTE_BGR);
        this.graphics = this.backBuffered.getGraphics();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void runAll() {
        GameObjectManager.instance.runAll();
        this.player.run();
        this.flyingEnemy.run();
        SceneManager.instance.performChangeSceneIfNeeded();
    }

    public void renderAll() {
        this.background.render(graphics);
        GameObjectManager.instance.renderAll(graphics);
        this.player.render(graphics);
        this.flyingEnemy.render(graphics);
        this.repaint();
    }
}
