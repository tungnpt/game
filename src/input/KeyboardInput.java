package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    public static KeyboardInput instance = new KeyboardInput();

    public boolean isSpace = false;
    public boolean isNum1 = false;
    public boolean isNum2 = false;
    public boolean isEnter = false;

    private KeyboardInput() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.isSpace = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD1 || e.getKeyCode() == KeyEvent.VK_1) {
            this.isNum1 = true;
            this.isNum2 = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 || e.getKeyCode() == KeyEvent.VK_2) {
            this.isNum2 = true;
            this.isNum1 = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.isEnter = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.isSpace = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.isEnter = false;
        }
    }
}
