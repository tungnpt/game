package game.player;

import base.GameObjectAttributes;
import base.GameObjectManager;
import game.island.FloatingIsland;

public class PlayerMove implements GameObjectAttributes<Player> {
    public PlayerMove() {

    }

    @Override
    public void run(Player gameObject) {
        for (int i = 0; i < GameObjectManager.instance.list.size(); i++) {
            if (gameObject.boxCollider.checkCollision(((FloatingIsland) GameObjectManager.instance.list.get(i)).boxCollider)) {
                gameObject.isFalling = false;
            }
        }
        if (!gameObject.isFalling) {
            gameObject.velocity.y = 15.5f;
            gameObject.velocity.y = -gameObject.velocity.y;
            gameObject.isFalling = true;
        } else {
            gameObject.velocity.y += gameObject.gravity;
        }
        gameObject.position.y += gameObject.velocity.y;
    }
}
