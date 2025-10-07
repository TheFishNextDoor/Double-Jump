package fun.sunrisemc.jump.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fun.sunrisemc.jump.PlayerProfile;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        PlayerProfile.remove(event.getPlayer());
    }
}