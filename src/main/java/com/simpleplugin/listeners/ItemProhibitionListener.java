package com.simpleplugin.listeners;

import com.simpleplugin.utils.ConfigManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

public class ItemProhibitionListener implements Listener { 

    private final ConfigManager configManager;

    public ItemProhibitionListener(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem().getItemStack();

        if (isProhibited(item)) {
            event.setCancelled(true);
            player.sendMessage(configManager.getConfig().getString("prohibited_items.message"));
        }
    }

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (isProhibited(item)) {
            event.setCancelled(true);
            player.sendMessage(configManager.getConfig().getString("prohibited_items.message"));
        }
    }

    private boolean isProhibited(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) return false;
        String itemName = item.getType().name();
        return configManager.getConfig().getStringList("prohibited_items.items").contains(itemName);
    }
}
