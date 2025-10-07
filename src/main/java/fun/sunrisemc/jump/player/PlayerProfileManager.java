package fun.sunrisemc.jump.player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PlayerProfileManager {

    private static HashMap<UUID, PlayerProfile> playerProfiles = new HashMap<>();

    public static PlayerProfile get(@NonNull Player player) {
        UUID uuid = player.getUniqueId();
        PlayerProfile playerProfile = playerProfiles.get(uuid);
        if (playerProfile == null) {
            playerProfile = new PlayerProfile(player);
            playerProfiles.put(uuid, playerProfile);
        }
        return playerProfile;
    }

    public static void unload(@NonNull Player player) {
        UUID uuid = player.getUniqueId();
        playerProfiles.remove(uuid);
    }
}