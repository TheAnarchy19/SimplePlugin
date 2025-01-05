package com.simpleplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnCommand implements CommandExecutor {

    private final FileConfiguration config;

    public SpawnCommand() {
        this.config = Bukkit.getPluginManager().getPlugin("SimplePlugin").getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            String worldName = config.getString("spawn.world", "world");
            double x = config.getDouble("spawn.x", 0);
            double y = config.getDouble("spawn.y", 100);
            double z = config.getDouble("spawn.z", 0);

            if (Bukkit.getWorld(worldName) != null) {
                Location spawnLocation = new Location(Bukkit.getWorld(worldName), x, y, z);
                player.teleport(spawnLocation);
                player.sendMessage("§a¡Te has teletransportado al spawn!");
            } else {
                player.sendMessage("§cEl mundo del spawn no existe.");
            }
        } else {
            sender.sendMessage("Este comando solo puede ser ejecutado por jugadores.");
        }
        return true;
    }
}
