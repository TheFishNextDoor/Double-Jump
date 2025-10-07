package fun.sunrisemc.jump.player;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import fun.sunrisemc.jump.DoubleJumpPlugin;
import fun.sunrisemc.jump.utils.PlayerUtils;

public class PlayerProfile {

    private final UUID UUID;

    private boolean doubleJumpEnabled = false;

    private boolean wantsDoubleJump;

    private boolean doubleJumpPermission = false;

    private boolean doubleJumpReady = false;

    private boolean wasOnGround = true;

    PlayerProfile(@NonNull Player player) {
        this.UUID = player.getUniqueId();

        this.wantsDoubleJump = !player.isFlying() && DoubleJumpPlugin.getMainConfig().ENABLED_BY_DEFAULT;

        checkPermissions();
    }

    public Optional<Player> getPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(UUID));
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

    public boolean hitTheGroundInFlyMode(Player player) {
        boolean isOnGround = PlayerUtils.isOnGround(player);
        boolean hitTheGroundInFlyMode = !this.wasOnGround && isOnGround && player.getAllowFlight() && isDoubleJumpReady();
        this.wasOnGround = isOnGround;
        return hitTheGroundInFlyMode;
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
}