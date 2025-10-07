package com.thefishnextdoor.jump;

import java.util.HashSet;
import java.util.Optional;
import java.util.logging.Logger;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.thefishnextdoor.jump.toolkit.RegistryUtils;

public class Settings {

    public final boolean ENABLED_BY_DEFAULT;
    public final double FORWARD_VELOCITY;
    public final double UP_VELOCITY;
    public final double FALL_DAMAGE_REDUCTION;
    public final HashSet<String> DISABLE_ON_COMMAND = new HashSet<>();
    public final Optional<Sound> SOUND;
    public final boolean SOUND_ENABLED;
    public final float SOUND_VOLUME;
    public final float SOUND_PITCH;

    public Settings(DoubleJump plugin) {
        FileConfiguration config = getPluginConfig(plugin);
        ENABLED_BY_DEFAULT = config.getBoolean("enabled-by-default");
        FORWARD_VELOCITY = config.getDouble("forward-velocity");
        UP_VELOCITY = config.getDouble("up-velocity");
        FALL_DAMAGE_REDUCTION = config.getDouble("fall-damage-reduction");
        DISABLE_ON_COMMAND.addAll(config.getStringList("disable-on-command"));
        
        String soundName = config.getString("sound.name");
        SOUND = RegistryUtils.getSoundByName(soundName);
        if (SOUND.isEmpty() && !soundName.isEmpty()) {
            Logger logger = plugin.getLogger();
            logger.warning("Invalid sound name: " + soundName);
        }
        SOUND_ENABLED = config.getBoolean("sound.enabled") && SOUND.isPresent();
        SOUND_VOLUME = (float) config.getDouble("sound.volume");
        SOUND_PITCH = (float) config.getDouble("sound.pitch");
    }

    private static FileConfiguration getPluginConfig(JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        return plugin.getConfig();
    }
}