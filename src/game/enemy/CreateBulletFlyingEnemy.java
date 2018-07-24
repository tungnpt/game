package game.enemy;

import action.*;
import base.GameObject;
import base.Vector2D;
import gameplay.GameCanvas;

public class CreateBulletFlyingEnemy extends GameObject {
    public CreateBulletFlyingEnemy() {
    }

    public void configAction() {
        this.addAction(
                new SequenceAction(
                        new LimitAction(10, new SequenceAction(
                                new WaitAction(100),
                                new ActionAdapter() {
                                    @Override
                                    public boolean run(GameObject owner) {
                                        Vector2D velocity = GameCanvas.player.position
                                                .subtract(GameCanvas.flyingEnemy.position)
                                                .normalize()
                                                .multiply(1.5f);
                                        WeaponEnemy bulletFlyingEnemy = new WeaponEnemy();
                                        bulletFlyingEnemy.position.set(GameCanvas.flyingEnemy.position);
                                        bulletFlyingEnemy.velocity.set(velocity);
                                        GameCanvas.flyingEnemy.weaponEnemies.add(bulletFlyingEnemy);
                                        //GameCanvas.flyingEnemy.addBullet(velocity);
                                        return true;
                                    }
                                }
                        )),
                        new RepeatActionForever(
                                new SequenceAction(
                                        new WaitAction(40),
                                        new ActionAdapter() {
                                            @Override
                                            public boolean run(GameObject owner) {
                                                WeaponEnemy bulletFlyingEnemy = new WeaponEnemy();
                                                bulletFlyingEnemy.position.set(GameCanvas.flyingEnemy.position);
                                                GameCanvas.flyingEnemy.weaponEnemies.add(bulletFlyingEnemy);
                                                //GameCanvas.flyingEnemy.addBullet(new Vector2D(0,1f));
                                                return true;
                                            }
                                        }
                                )
                        )
                )
        );
    }
}
