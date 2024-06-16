package com.thefishnextdoor.jump.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.thefishnextdoor.jump.DoubleJump;
import com.thefishnextdoor.jump.PlayerProfile;

public class PlayerCommandPreprocess implements Listener {
    
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().toLowerCase().replaceFirst("/", "");
        if (DoubleJump.getSettings().DISABLE_ON_COMMAND.contains(message)) {
            PlayerProfile.get(event.getPlayer()).setWantsDoubleJump(false);
        }
    }
}