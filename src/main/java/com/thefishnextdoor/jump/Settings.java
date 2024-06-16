package com.thefishnextdoor.jump;

import java.util.HashSet;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Settings {

    public final boolean ENABLED_BY_DEFAULT;
    public final double FORWARD_VELOCITY;
    public final double UP_VELOCITY;
    public final double FALL_DAMAGE_REDUCTION;
    public final HashSet<String> DISABLE_ON_COMMAND = new HashSet<>();

    public Settings(DoubleJump plugin) {
        FileConfiguration config = getPluginConfig(plugin);
        ENABLED_BY_DEFAULT = config.getBoolean("enabled-by-default");
        FORWARD_VELOCITY = config.getDouble("forward-velocity");
        UP_VELOCITY = config.getDouble("up-velocity");
        FALL_DAMAGE_REDUCTION = config.getDouble("fall-damage-reduction");
        DISABLE_ON_COMMAND.addAll(config.getStringList("disable-on-command"));
    }

    private static FileConfiguration getPluginConfig(JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        return plugin.getConfig();
    }
}