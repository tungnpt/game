package game.player;

import base.GameObjectAttributes;
import base.Vector2D;
import input.KeyboardInput;
import utils.Utils;

import javax.sound.sampled.Clip;

public class PlayerAttack implements GameObjectAttributes<Player> {
    public int count =7;
    private Clip punchSound = Utils.loadAudio("resources/audio/shoot.wav");
    @Override
    public void run(Player gameObject) {
        gameObject.sword.position.set(gameObject.position.x + 60, gameObject.position.y);
        gameObject.specialSkill.position.set(gameObject.position.x-240, gameObject.position.y-470);
        if (gameObject.energy >3){
            gameObject.energy =3;
        }
        if (KeyboardInput.instance.isNum1){
            gameObject.equippedSword = true;
            gameObject.equippedGun = false;
        }
        if (KeyboardInput.instance.isNum2){
            gameObject.equippedGun = true;
            gameObject.equippedSword = false;
        }
        if (KeyboardInput.instance.isEnter) {
            gameObject.specialSkill.run();
        }
        else {
            gameObject.specialSkill.isAlive =  false;
        }
        if (KeyboardInput.instance.isSpace) {
            gameObject.distance += 25;
            gameObject.point = gameObject.distance / 400;

//            if (gameObject.equippedGun) {
//                if (gameObject.frameCounter.run()) {
//                    this.addBullet(gameObject);
//                    gameObject.frameCounter.reset();
//                }
//            }
            if (gameObject.equippedGun) {
                if (this.count == 7){
                    this.addBullet(gameObject);
                    this.count = 0;
                }
                this.count += 1;
            }
            else {
                this.count = 7;
            }
            if (gameObject.equippedSword) {
                gameObject.sword.run();
                this.punchSound.loop(1);
                this.punchSound.start();
            }
        }
        for (int i = 0; i < gameObject.bulletPlayers.size(); i++) {
            gameObject.bulletPlayers.get(i).run();
            if (gameObject.bulletPlayers.get(i).position.x >= 1920) {
                gameObject.bulletPlayers.get(i).isAlive = false;
            }
        }
    }
    public void addBullet(Player gameObject) {
        for (int i = 0; i < gameObject.bulletPlayers.size(); i++) {
            if (gameObject.bulletPlayers.get(i).isAlive == false) {
                gameObject.bulletPlayers.get(i).isAlive = true;
                gameObject.bulletPlayers.get(i).position.set(gameObject.position.x + gameObject.width, gameObject.position.y + gameObject.height/2);
                return;
            }
        }
        BulletPlayer bulletPlayer = new BulletPlayer();
        bulletPlayer.position.set(gameObject.position);
        gameObject.bulletPlayers.add(bulletPlayer);
        return;
    }
}
