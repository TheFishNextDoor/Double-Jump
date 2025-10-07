package fun.sunrisemc.jump;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import fun.sunrisemc.jump.command.DoubleJumpCommand;
import fun.sunrisemc.jump.config.MainConfig;
import fun.sunrisemc.jump.event.EntityDamage;
import fun.sunrisemc.jump.event.PlayerChangedWorld;
import fun.sunrisemc.jump.event.PlayerCommandPreprocess;
import fun.sunrisemc.jump.event.PlayerMove;
import fun.sunrisemc.jump.event.PlayerQuit;
import fun.sunrisemc.jump.event.PlayerToggleFlight;

public class DoubleJumpPlugin extends JavaPlugin {

    private static DoubleJumpPlugin instance;

    private static MainConfig mainConfig;

    public void onEnable() {
        loadMainConfig();

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new PlayerToggleFlight(), this);
        pluginManager.registerEvents(new EntityDamage(), this);
        pluginManager.registerEvents(new PlayerQuit(), this);
        pluginManager.registerEvents(new PlayerChangedWorld(), this);
        pluginManager.registerEvents(new PlayerCommandPreprocess(), this);

        registerCommand("doublejump", new DoubleJumpCommand());
        
        logInfo("Plugin enabled");
    }

    public void onDisable() {
        logInfo("Plugin disabled");
    }

    public static void logInfo(@NonNull String message) {
        getInstance().getLogger().info(message);
    }

    public static void logWarning(@NonNull String message) {
        getInstance().getLogger().warning(message);
    }

    public static void logSevere(@NonNull String message) {
        getInstance().getLogger().severe(message);
    }

    public static DoubleJumpPlugin getInstance() {
        return instance;
    }

    public static MainConfig getMainConfig() {
        return mainConfig;
    }

    public static void loadMainConfig() {
        mainConfig = new MainConfig();
    }

    private boolean registerCommand(@NonNull String commandName, @NonNull CommandExecutor commandExecutor) {
        PluginCommand command = getCommand(commandName);
        if (command == null) {
            logSevere("Command '" + commandName + "' not found in plugin.yml.");
            return false;
        }

        command.setExecutor(commandExecutor);

        if (commandExecutor instanceof TabCompleter) {
            command.setTabCompleter((TabCompleter) commandExecutor);
        }

        return true;
    }
}