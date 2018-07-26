package game.enemy;

import base.Vector2D;
import gameplay.GameCanvas;

public class BulletEnemy extends WeaponEnemy{
    public BulletEnemy(){
        super();
        Vector2D velo = GameCanvas.player.position
                .subtract(GameCanvas.flyingEnemy.position)
                .normalize()
                .multiply(10f);
        this.velocity.set(velo);
    }
    public void run(){
        super.run();
        if (this.boxCollider.checkCollision(GameCanvas.player.sword.boxCollider)){
            this.isAlive = false;
        }
    }
}
