package com.thefishnextdoor.jump.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.thefishnextdoor.jump.DoubleJump;
import com.thefishnextdoor.jump.PlayerProfile;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PlayerProfile playerProfile = PlayerProfile.get(player);
        if (playerProfile.hitTheGroundInFlyMode(player)) {
            double damage = ((player.getFallDistance() - 3.0) * 0.6) - DoubleJump.getSettings().FALL_DAMAGE_REDUCTION;
            if (damage > 0) {
                player.damage(damage);
            }
        }
        else if (playerProfile.isDoubleJumpReady()) {
            if (!player.getAllowFlight()) {
                player.setAllowFlight(true);
            }
        }
        else if (player.isOnGround()) {
            playerProfile.setDoubleJumpReady(true);
        }
    }
}