package fun.sunrisemc.jump.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PlayerUtils {

    public static boolean isOnGround(@NonNull Player player) {
        Entity entity = (Entity) player;
        return entity.isOnGround();
    }
}