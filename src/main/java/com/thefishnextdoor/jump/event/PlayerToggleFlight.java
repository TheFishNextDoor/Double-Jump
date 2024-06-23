package com.thefishnextdoor.jump.event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import com.thefishnextdoor.jump.DoubleJump;
import com.thefishnextdoor.jump.PlayerProfile;
import com.thefishnextdoor.jump.Settings;

public class PlayerToggleFlight implements Listener {

    @EventHandler
    public void onFlyToggle(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.isFlying()) {
            return;
        }
        if (player.getGameMode() != GameMode.SURVIVAL && player.getGameMode() != GameMode.ADVENTURE) {
            return;
        }
        PlayerProfile playerProfile = PlayerProfile.get(player);
        if (playerProfile.isDoubleJumpReady()) {
            Settings settings = DoubleJump.getSettings();
            player.setVelocity(player.getLocation().getDirection().multiply(settings.FORWARD_VELOCITY).setY(settings.UP_VELOCITY));
            playerProfile.setDoubleJumpReady(false);
            event.setCancelled(true);
            player.setAllowFlight(false);
            if (settings.SOUND_ENABLED && settings.SOUND != null) {
                player.getWorld().playSound(player.getLocation(), settings.SOUND, (float) settings.SOUND_VOLUME, (float) settings.SOUND_PITCH);
            }
        }
    }
}