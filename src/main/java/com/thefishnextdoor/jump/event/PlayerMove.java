package com.thefishnextdoor.jump.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.thefishnextdoor.jump.PlayerProfile;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PlayerProfile playerProfile = PlayerProfile.get(player);
        if (playerProfile.isDoubleJumpReady()) {
            if (!player.getAllowFlight()) {
                player.setAllowFlight(true);
            }
        }
        else {
            if (player.isOnGround()) {
                playerProfile.setDoubleJumpReady(true);
            }
        }
    }
}