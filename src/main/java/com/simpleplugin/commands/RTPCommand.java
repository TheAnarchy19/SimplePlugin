package com.simpleplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.UUID;

public class RTPCommand implements CommandExecutor {

    private final FileConfiguration config;
    private final HashMap<UUID, Long> cooldowns;

    public RTPCommand() {
        this.config = Bukkit.getPluginManager().getPlugin("SimplePlugin").getConfig();
        this.cooldowns = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("simpleplugin.rtp")) {
                player.sendMessage(config.getString("rtp.messages.no_permission", "§cNo tienes permiso para usar este comando."));
                return false;
            }

            if (isOnCooldown(player)) {
                long remainingTime = getCooldownTime(player);
                player.sendMessage(config.getString("rtp.messages.wait_time", "§cDebes esperar " + remainingTime + " segundos antes de usar el comando nuevamente."));
                return false;
            }

            String worldName = config.getString("rtp.world", "world");
            int radius = config.getInt("rtp.radius", 1000);
            String message = config.getString("rtp.message", "§a¡Has sido teletransportado aleatoriamente!");
            List<String> blacklistedWorlds = config.getStringList("rtp.blacklist.worlds");
            List<String> blacklistedBiomes = config.getStringList("rtp.blacklist.biomes");
            int cooldownTime = config.getInt("rtp.cooldown", 30);

            if (blacklistedWorlds.contains(player.getWorld().getName())) {
                player.sendMessage(config.getString("rtp.messages.blacklisted_world", "§cNo puedes teletransportarte a este mundo."));
                return false;
            }

            Location randomLocation = getRandomLocation(worldName, radius, blacklistedBiomes);
            if (randomLocation == null) {
                player.sendMessage(config.getString("rtp.messages.no_valid_location", "§cNo se pudo encontrar un lugar válido para teletransportarse."));
                return false;
            }

            randomLocation.getWorld().loadChunk(randomLocation.getBlockX() >> 4, randomLocation.getBlockZ() >> 4);

            player.teleport(randomLocation);
            player.sendMessage(message);

            setCooldown(player, cooldownTime);

        } else {
            sender.sendMessage(config.getString("rtp.messages.console_command", "Este comando solo puede ser ejecutado por jugadores."));
        }
        return true;
    }

    private Location getRandomLocation(String worldName, int radius, List<String> blacklistedBiomes) {
        Random random = new Random();
        int attempts = 0;

        while (attempts < 100) {  
            int x = random.nextInt(radius * 2) - radius;
            int z = random.nextInt(radius * 2) - radius;

            Location randomLocation = new Location(Bukkit.getWorld(worldName), x, 100, z);
            randomLocation.setY(randomLocation.getWorld().getHighestBlockYAt(randomLocation));

            Biome biome = randomLocation.getBlock().getBiome();
            if (!blacklistedBiomes.contains(biome.name())) {
                return randomLocation;
            }

            attempts++;
        }

        return null;  
    }

    private boolean isOnCooldown(Player player) {
        return cooldowns.containsKey(player.getUniqueId()) &&
                cooldowns.get(player.getUniqueId()) > System.currentTimeMillis();
    }

    private long getCooldownTime(Player player) {
        long timeLeft = (cooldowns.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000;
        return timeLeft <= 0 ? 0 : timeLeft;
    }

    private void setCooldown(Player player, int seconds) {
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + (seconds * 1000L));
    }
}
