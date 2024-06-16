package com.thefishnextdoor.jump;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerProfile {

    private static HashMap<UUID, PlayerProfile> playerProfiles = new HashMap<>();

    private final UUID uuid;

    private boolean doubleJumpEnabled = false;

    private boolean wantsDoubleJump;

    private boolean doubleJumpPermission = false;

    private boolean doubleJumpReady = false;

    public PlayerProfile(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        this.uuid = player.getUniqueId();
        this.wantsDoubleJump = !player.isFlying();
        checkPermissions();
        playerProfiles.put(uuid, this);
    }

    public Optional<Player> getPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(uuid));
    }

    public boolean doubleJumpEnabled() {
        return doubleJumpEnabled;
    }

    public boolean wantsDoubleJump() {
        return wantsDoubleJump;
    }

    public void setWantsDoubleJump(boolean wantsDoubleJump) {
        this.wantsDoubleJump = wantsDoubleJump;
        if (wantsDoubleJump) {
            getPlayer().ifPresent(player -> player.setAllowFlight(false));
        }
        setDoubleJumpStatus();
    }

    public void checkPermissions() {
        Optional<Player> player = getPlayer();
        if (!player.isPresent()) {
            return;
        }
        doubleJumpPermission = player.get().hasPermission("doublejump.use");
        setDoubleJumpStatus();
    }

    public boolean isDoubleJumpReady() {
        return doubleJumpEnabled && doubleJumpReady;
    }

    public void setDoubleJumpReady(boolean doubleJumpReady) {
        this.doubleJumpReady = doubleJumpReady;
    }

    private void setDoubleJumpStatus() {
        boolean shouldBeEnabled = doubleJumpPermission && wantsDoubleJump;
        if (this.doubleJumpEnabled == shouldBeEnabled) {
            return;
        }
        this.doubleJumpEnabled = shouldBeEnabled;
        if (!doubleJumpEnabled) {
            getPlayer().ifPresent(player -> player.setAllowFlight(false));
        }
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