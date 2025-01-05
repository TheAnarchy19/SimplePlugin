package com.simpleplugin.listeners;

import com.simpleplugin.utils.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class PlayerEventListener implements Listener {

    private final ConfigManager configManager;

    public PlayerEventListener(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String welcomeMessage = configManager.getConfig().getString("events.player_join.message", "¡Bienvenido al servidor!");
        
        welcomeMessage = welcomeMessage.replace("%player%", player.getName());

        player.sendMessage(welcomeMessage);

        boolean logJoin = configManager.getConfig().getBoolean("events.player_join.log", true);
        if (logJoin) {
            Bukkit.getLogger().info("Jugador se unió: " + player.getName());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        String quitMessage = configManager.getConfig().getString("events.player_quit.message", "¡Adiós!");
        
        quitMessage = quitMessage.replace("%player%", player.getName());

        player.sendMessage(quitMessage);

        boolean logQuit = configManager.getConfig().getBoolean("events.player_quit.log", true);
        if (logQuit) {
            Bukkit.getLogger().info("Jugador se desconectó: " + player.getName());
        }
    }
}
