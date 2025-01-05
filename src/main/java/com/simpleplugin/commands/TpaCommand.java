package com.simpleplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.simpleplugin.utils.*;

public class TpaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null && target.isOnline()) {
                    // Enviar solicitud de teletransportación
                    target.sendMessage(player.getName() + " te ha solicitado teletransportarse a ti. Usa /tpaccept o /tpdeny.");
                    player.sendMessage("Has solicitado teletransportarte a " + target.getName() + ". Esperando respuesta...");
                    
                    // Guardar la solicitud en algún tipo de almacenamiento (por ejemplo, un mapa)
                    TeleportRequestManager.addRequest(player, target);
                } else {
                    player.sendMessage("El jugador no está online.");
                }
            } else {
                player.sendMessage("Uso correcto: /tpa <jugador>");
            }
        }
        return false;
    }
}
