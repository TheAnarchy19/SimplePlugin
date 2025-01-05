package com.simpleplugin.commands;

import com.simpleplugin.utils.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;

public class AnalyzeCommand implements CommandExecutor {

    private final ConfigManager configManager;

    // Constructor que recibe un ConfigManager
    public AnalyzeCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("§aEstadísticas del servidor:");
            try {
                Method getTPSMethod = Bukkit.getServer().getClass().getMethod("getTPS");
                double[] tps = (double[]) getTPSMethod.invoke(Bukkit.getServer());
                player.sendMessage("§eTPS: " + String.format("%.2f", tps[0]));
            } catch (Exception e) {
                player.sendMessage("§cError al obtener el TPS.");
            }

            int entityCount = player.getWorld().getEntities().size();
            player.sendMessage("§eEntidades cargadas: " + entityCount);
        } else {
            sender.sendMessage("Este comando solo puede ser ejecutado por jugadores.");
        }
        return true;
    }
}
