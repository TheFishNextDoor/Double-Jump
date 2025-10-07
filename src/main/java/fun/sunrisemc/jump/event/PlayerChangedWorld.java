package fun.sunrisemc.jump.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import fun.sunrisemc.jump.player.PlayerProfileManager;

public class PlayerChangedWorld implements Listener {

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        PlayerProfileManager.get(event.getPlayer()).checkPermissions();
    }
}