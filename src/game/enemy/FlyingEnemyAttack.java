package game.enemy;

import base.GameObjectAttributes;
import base.Vector2D;
import input.KeyboardInput;

public class FlyingEnemyAttack implements GameObjectAttributes<FlyingEnemy> {
    @Override
    public void run(FlyingEnemy gameObject) {
        if (gameObject.timeComeBack.run()) {
            if (gameObject.frameCounter.run()) {
                gameObject.velocity.set(new Vector2D(-12f, 0));
                gameObject.DropBomb();
                if (KeyboardInput.instance.isSpace) {
                    gameObject.position.x -= 25;
                }
                gameObject.position.addUp(gameObject.velocity);
            } else {
                gameObject.velocity.set(new Vector2D(-1f, 0));
                gameObject.shootBullet();
                gameObject.position.addUp(gameObject.velocity);
            }
            gameObject.boxCollider.position.set(gameObject.position);
            if (gameObject.position.x + gameObject.width <=0){
                gameObject.timeComeBack.reset();
                gameObject.setupAgian();
            }
        }
        gameObject.weaponEnemies.forEach(bulletFlyingEnemy -> bulletFlyingEnemy.run());
    }
}
