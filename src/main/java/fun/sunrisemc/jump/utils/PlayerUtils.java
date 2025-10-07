package fun.sunrisemc.jump.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static boolean isOnGround(Player player) {
        Entity entity = (Entity) player;
        return entity.isOnGround();
    }
}