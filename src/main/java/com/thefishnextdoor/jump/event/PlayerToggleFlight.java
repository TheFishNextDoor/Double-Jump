package com.thefishnextdoor.jump.event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import com.thefishnextdoor.jump.PlayerProfile;

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
            player.setVelocity(player.getLocation().getDirection().multiply(0.9).setY(0.8));
            playerProfile.setDoubleJumpReady(false);
            event.setCancelled(true);
            player.setAllowFlight(false);
        }
    }
}