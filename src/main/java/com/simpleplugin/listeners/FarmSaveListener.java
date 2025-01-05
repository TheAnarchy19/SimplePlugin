package com.simpleplugin.listeners;

import com.simpleplugin.utils.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.Plugin;

public class FarmSaveListener implements Listener {

    private final ConfigManager configManager;

    public FarmSaveListener(Plugin plugin) {
        this.configManager = new ConfigManager(plugin);
    }

    @EventHandler
    public void onRedstoneUpdate(BlockRedstoneEvent event) {
        int redstoneLimit = configManager.getConfig().getInt("optimize.redstone-limit", 15);
        if (event.getNewCurrent() > redstoneLimit) {
            event.setNewCurrent(0);
            event.getBlock().getWorld().getPlayers().forEach(player -> 
                player.sendMessage("§cRedstone desactivado temporalmente en esta área por sobrecarga."));
        }
    }
}
