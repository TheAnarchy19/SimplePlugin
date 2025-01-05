package com.simpleplugin.commands;

import com.simpleplugin.utils.TeleportRequestManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TpaAcceptCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (TeleportRequestManager.hasRequest(player)) {
                Player requester = TeleportRequestManager.getRequester(player);
                
                // Teletransportar al jugador
                player.teleport(requester);
                player.sendMessage("Te has teletransportado a " + requester.getName());
                requester.sendMessage(player.getName() + " ha aceptado tu solicitud de teletransportación.");
                
                // Eliminar la solicitud
                TeleportRequestManager.removeRequest(player);
            } else {
                player.sendMessage("No tienes ninguna solicitud de teletransportación pendiente.");
            }
        }
        return false;
    }
}
