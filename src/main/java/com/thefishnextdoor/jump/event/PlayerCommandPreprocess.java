package com.thefishnextdoor.jump.event;

import java.util.HashSet;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.thefishnextdoor.jump.PlayerProfile;

public class PlayerCommandPreprocess implements Listener {

    private HashSet<String> commands = new HashSet<>(List.of("fly", "flight"));
    
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().toLowerCase().replaceFirst("/", "");
        if (commands.contains(message)) {
            PlayerProfile.get(event.getPlayer()).setWantsDoubleJump(false);
        }
    }
}