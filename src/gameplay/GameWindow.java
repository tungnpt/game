package gameplay;

import input.KeyboardInput;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {
    GameCanvas gameCanvas;
    long lastTime = 0;

    public GameWindow() {
        this.setSize(1920, 1200);

        this.gameCanvas = new GameCanvas();

        this.add(this.gameCanvas);

        this.event();

        this.setVisible(true);
    }

    private void event() {
        this.keyboardEvent();
        this.windowEvent();
    }

    private void keyboardEvent() {
//        this.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    gameCanvas.player.position.x -= gameCanvas.player.velocity.x;
//                    gameCanvas.player.onIsland=false;
//                }
//                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                    gameCanvas.player.position.x += gameCanvas.player.velocity.x;
//                    gameCanvas.player.onIsland=false;
//                }
//                if (e.getKeyCode() == KeyEvent.VK_UP) {
//                    gameCanvas.player.position.y -= gameCanvas.player.velocity.y;
//                    gameCanvas.player.onIsland=false;
//                }
//                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//                    if(gameCanvas.player.onIsland==false){
//                        gameCanvas.player.position.y += gameCanvas.player.velocity.y;
//                    }else{
//                        int velo = (int)gameCanvas.floatingIsland.position.y-(int)gameCanvas.player.position.y-(int)gameCanvas.player.height;
//                        gameCanvas.player.position.y += velo;
//                        gameCanvas.player.onIsland=false;
//                    }
//                }
//                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//                    gameCanvas.floatingIsland.position.addUp(gameCanvas.floatingIsland.velocity);
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//
//                }
//            }
//        });
        this.addKeyListener(KeyboardInput.instance);
    }

    public void gameLoop() {
        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - this.lastTime >= 17_000_000) {
                this.gameCanvas.renderAll();
                this.gameCanvas.runAll();
                this.lastTime = currentTime;
            }

        }
    }
    private void windowEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }
}
