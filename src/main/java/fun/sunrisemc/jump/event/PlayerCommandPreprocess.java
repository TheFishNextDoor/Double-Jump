package fun.sunrisemc.jump.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import fun.sunrisemc.jump.DoubleJumpPlugin;
import fun.sunrisemc.jump.player.PlayerProfile;

public class PlayerCommandPreprocess implements Listener {
    
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().toLowerCase().replaceFirst("/", "");
        if (DoubleJumpPlugin.getMainConfig().DISABLE_ON_COMMAND.contains(message)) {
            PlayerProfile.get(event.getPlayer()).setWantsDoubleJump(false);
        }
    }
}