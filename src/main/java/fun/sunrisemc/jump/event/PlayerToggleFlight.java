package fun.sunrisemc.jump.event;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import fun.sunrisemc.jump.DoubleJumpPlugin;
import fun.sunrisemc.jump.config.MainConfig;
import fun.sunrisemc.jump.player.PlayerProfile;

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
            MainConfig settings = DoubleJumpPlugin.getSettings();
            player.setVelocity(player.getLocation().getDirection().multiply(settings.FORWARD_VELOCITY).setY(settings.UP_VELOCITY));
            playerProfile.setDoubleJumpReady(false);
            event.setCancelled(true);
            player.setAllowFlight(false);
            if (settings.SOUND_ENABLED && settings.SOUND.isPresent()) {
                World world = player.getWorld();
                Location location = player.getLocation();
                world.playSound(location, settings.SOUND.get(), (float) settings.SOUND_VOLUME, (float) settings.SOUND_PITCH);
            }
        }
    }
}