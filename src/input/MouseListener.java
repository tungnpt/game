package input;

import scene.GameOverScene;
import scene.GamePlayScene;
import scene.GameStartScene;
import scene.SceneManager;

import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + ", " + e.getY());
        if (SceneManager.instance.getCurrentScene() instanceof GameStartScene) {
            if (e.getX()>=700 && e.getX()<=1180 && e.getY()>=540 && e.getY()<=650) {
                SceneManager.instance.changeScene(new GamePlayScene());
            }
        }
        if (SceneManager.instance.getCurrentScene() instanceof GameOverScene){
            if (e.getX()>=780 && e.getX()<=1220 && e.getY() >= 360 && e.getY()<=440){
                SceneManager.instance.changeScene(new GamePlayScene());
            }
            if (e.getX()>=780 && e.getX()<=1220 && e.getY() >= 660 && e.getY()<=730) {
                System.exit(1);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
