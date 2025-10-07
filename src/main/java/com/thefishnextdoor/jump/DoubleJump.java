package com.thefishnextdoor.jump;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thefishnextdoor.jump.command.DoubleJumpCommand;
import com.thefishnextdoor.jump.event.EntityDamage;
import com.thefishnextdoor.jump.event.PlayerChangedWorld;
import com.thefishnextdoor.jump.event.PlayerCommandPreprocess;
import com.thefishnextdoor.jump.event.PlayerMove;
import com.thefishnextdoor.jump.event.PlayerQuit;
import com.thefishnextdoor.jump.event.PlayerToggleFlight;

public class DoubleJump extends JavaPlugin {

    private static Settings settings;

    public void onEnable() {
        loadConfig();

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new PlayerToggleFlight(), this);
        pluginManager.registerEvents(new EntityDamage(), this);
        pluginManager.registerEvents(new PlayerQuit(), this);
        pluginManager.registerEvents(new PlayerChangedWorld(), this);
        pluginManager.registerEvents(new PlayerCommandPreprocess(), this);

        registerCommand("doublejump", new DoubleJumpCommand(this));
        
        getLogger().info("Plugin enabled");
    }

    public void onDisable() {
        getLogger().info("Plugin disabled");
    }

    public void loadConfig() {
        settings = new Settings(this);
    }

    public static Settings getSettings() {
        return settings;
    }

    private void registerCommand(String commandName, CommandExecutor commandHandler) {
        if (commandName == null) {
            throw new IllegalArgumentException("commandName cannot be null");
        }
        if (commandHandler == null) {
            throw new IllegalArgumentException("commandHandler cannot be null");
        }
        PluginCommand command = getCommand(commandName);
        if (command == null) {
            getLogger().warning("Failed to register command: " + commandName);
            return;
        }
        command.setExecutor(commandHandler);
        if (commandHandler instanceof TabCompleter) {
            command.setTabCompleter((TabCompleter) commandHandler);
        }
    }
}