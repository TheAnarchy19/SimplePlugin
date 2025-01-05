package com.simpleplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public class SetSpawnCommand implements CommandExecutor {

    private final FileConfiguration config;

    public SetSpawnCommand() {
        this.config = Bukkit.getPluginManager().getPlugin("SimplePlugin").getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("simpleplugin.setspawn")) {
                // Get player's location
                Location playerLocation = player.getLocation();

                // Save the player's location in config.yml
                config.set("spawn.world", playerLocation.getWorld().getName());
                config.set("spawn.x", playerLocation.getX());
                config.set("spawn.y", playerLocation.getY());
                config.set("spawn.z", playerLocation.getZ());

                Bukkit.getPluginManager().getPlugin("SimplePlugin").saveConfig();

                player.sendMessage("§a¡El spawn ha sido establecido correctamente en tu ubicación actual!");
            } else {
                player.sendMessage("§cNo tienes permiso para usar este comando.");
            }
        } else {
            sender.sendMessage("Este comando solo puede ser ejecutado por jugadores.");
        }
        return true;
    }
}
