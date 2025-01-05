package com.simpleplugin.commands;

import com.simpleplugin.utils.TeleportRequestManager;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TpaDenyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (TeleportRequestManager.hasRequest(player)) {
                Player requester = TeleportRequestManager.getRequester(player);

                // Informar al jugador que la solicitud fue denegada
                player.sendMessage("Has denegado la solicitud de teletransportación de " + requester.getName());
                requester.sendMessage(player.getName() + " ha denegado tu solicitud de teletransportación.");

                // Eliminar la solicitud
                TeleportRequestManager.removeRequest(player);
            } else {
                player.sendMessage("No tienes ninguna solicitud de teletransportación pendiente.");
            }
        }
        return false;
    }
}
