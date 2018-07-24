package game.enemy;

import base.Vector2D;
import gameplay.GameCanvas;

public class BombEnemy extends WeaponEnemy {
    public BombEnemy(){
        super();
        this.velocity.set(new Vector2D(0,1f));
    }
}
