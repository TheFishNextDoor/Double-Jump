package fun.sunrisemc.jump.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import fun.sunrisemc.jump.PlayerProfile;

public class PlayerChangedWorld implements Listener {

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        PlayerProfile.get(event.getPlayer()).checkPermissions();
    }
}