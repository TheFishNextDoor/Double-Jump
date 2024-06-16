package com.thefishnextdoor.jump.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.thefishnextdoor.jump.PlayerProfile;

import net.md_5.bungee.api.ChatColor;

public class DoubleJumpCommand implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return List.of("enable", "disable");
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
            return true;
        }
        Player player = (Player) sender;
        PlayerProfile playerProfile = PlayerProfile.get(player);
        boolean enableDoubleJump;
        if (args.length == 0) {
            enableDoubleJump = !playerProfile.isDoubleJumpEnabled();
        }
        else {
            if (args[0].equalsIgnoreCase("enable")) {
                enableDoubleJump = true;
            }
            else if (args[0].equalsIgnoreCase("disable")) {
                enableDoubleJump = false;
            }
            else {
                return false;
            }
        }
        playerProfile.setDoubleJumpEnabled(enableDoubleJump);
        if (enableDoubleJump) {
            player.sendMessage(ChatColor.GREEN + "Double jump enabled.");
        }
        else {
            player.sendMessage(ChatColor.DARK_GREEN + "Double jump disabled.");
        }
        return true;
    }
}