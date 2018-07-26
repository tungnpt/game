package game.player;

import base.GameObjectAttributes;
import base.Vector2D;
import input.KeyboardInput;

public class PlayerAttack implements GameObjectAttributes<Player> {
    @Override
    public void run(Player gameObject) {
        gameObject.sword.position.set(new Vector2D(gameObject.position.x + 60, gameObject.position.y));
        gameObject.specialSkill.position.set(gameObject.position.x-240, gameObject.position.y-470);
        if (gameObject.energy >30){
            gameObject.energy =30;
        }
        if (KeyboardInput.instance.isNum1){
            gameObject.equippedSword = true;
            gameObject.equippedGun = false;
        }
        if (KeyboardInput.instance.isNum2){
            gameObject.equippedGun = true;
            gameObject.equippedSword = false;
        }
        if (gameObject.energy == 30) {
            gameObject.specialSkill.run();
        }
        if (KeyboardInput.instance.isSpace) {
            gameObject.distance += 25;
            gameObject.point = gameObject.distance / 400;
            //System.out.println(this.point);

            if (gameObject.equippedGun) {
                if (gameObject.frameCounter.run()) {
                    gameObject.addBullet();
                    gameObject.frameCounter.reset();
                }
            }
            if (gameObject.equippedSword) {
                gameObject.sword.run();
            }
        }
        for (int i = 0; i < gameObject.bulletPlayers.size(); i++) {
            gameObject.bulletPlayers.get(i).run();
            if (gameObject.bulletPlayers.get(i).position.x >= 1920) {
                gameObject.bulletPlayers.get(i).isAlive = false;
            }
            //System.out.println(this.bulletPlayers.size());
        }
    }
}
