package com.thefishnextdoor.jump;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerProfile {

    private static HashMap<UUID, PlayerProfile> playerProfiles = new HashMap<>();

    private final UUID uuid;

    private boolean doubleJumpEnabled = true;

    private boolean doubleJumpReady = false;

    public PlayerProfile(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        this.uuid = player.getUniqueId();
        checkPermissions();
        playerProfiles.put(uuid, this);
    }

    public boolean isDoubleJumpEnabled() {
        return doubleJumpEnabled;
    }

    public void setDoubleJumpEnabled(boolean doubleJumpEnabled) {
        if (this.doubleJumpEnabled == doubleJumpEnabled) {
            return;
        }
        this.doubleJumpEnabled = doubleJumpEnabled;
        if (!doubleJumpEnabled) {
            getPlayer().ifPresent(player -> player.setAllowFlight(false));
        }
    }

    public boolean isDoubleJumpReady() {
        return doubleJumpReady;
    }

    public void setDoubleJumpReady(boolean doubleJumpReady) {
        this.doubleJumpReady = doubleJumpReady;
    }

    public void checkPermissions() {
        if (doubleJumpEnabled) {
            getPlayer().ifPresent(player -> setDoubleJumpEnabled(player.hasPermission("doublejump")));
        }   
    }

    public Optional<Player> getPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(uuid));
    }

    public static PlayerProfile get(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        PlayerProfile playerProfile = playerProfiles.get(player.getUniqueId());
        if (playerProfile == null) {
            return new PlayerProfile(player);
        }
        return playerProfile;
    }

    public static void remove(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        playerProfiles.remove(player.getUniqueId());
    }
}