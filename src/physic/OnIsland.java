package physic;

import base.GameObject;
import game.island.FloatingIsland;

public class OnIsland {
    public static boolean checkOnIsland(GameObject player, FloatingIsland floatingIsland){
        if((player.position.x>=floatingIsland.position.x
                && player.position.x+25<=floatingIsland.position.x+floatingIsland.width
                && player.position.y+player.height+player.velocity.y>=floatingIsland.position.y+10
        )
                || (player.position.x+player.width-25>=floatingIsland.position.x
                && player.position.x+player.width<=floatingIsland.position.x+floatingIsland.width
                && player.position.y+player.height+player.velocity.y>=floatingIsland.position.y+10
        ) ){
            return true;
        }
        return false;
    }
}
