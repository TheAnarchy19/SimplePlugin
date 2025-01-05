package com.simpleplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import com.simpleplugin.commands.*;
import com.simpleplugin.listeners.*;
import com.simpleplugin.utils.*;

public class Main extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        // Mensaje de inicio
        getLogger().info(ChatColor.YELLOW + "███████╗██╗███╗   ███╗██████╗ ██╗     ███████╗██████╗ ██╗     ██╗   ██╗ ██████╗ ██╗███╗   ██╗");
        getLogger().info(ChatColor.YELLOW + "██╔════╝██║████╗ ████║██╔══██╗██║     ██╔════╝██╔══██╗██║     ██║   ██║██╔════╝ ██║████╗  ██║");
        getLogger().info(ChatColor.YELLOW + "███████╗██║██╔████╔██║██████╔╝██║     █████╗  ██████╔╝██║     ██║   ██║██║  ███╗██║██╔██╗ ██║");
        getLogger().info(ChatColor.YELLOW + "╚════██║██║██║╚██╔╝██║██╔═══╝ ██║     ██╔══╝  ██╔═══╝ ██║     ██║   ██║██║   ██║██║██║╚██╗██║");
        getLogger().info(ChatColor.YELLOW + "███████║██║██║ ╚═╝ ██║██║     ███████╗███████╗██║     ███████╗╚██████╔╝╚██████╔╝██║██║ ╚████║");
        getLogger().info(ChatColor.YELLOW + "╚══════╝╚═╝╚═╝     ╚═╝╚═╝     ╚══════╝╚══════╝╚═╝     ╚══════╝ ╚═════╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝");
        getLogger().info("");
        getLogger().info(ChatColor.GREEN + "Plugin iniciado en la versiòn 0.1!");
        getLogger().info(ChatColor.GREEN + "Autor: TheAnarchy19");
        
        saveDefaultConfig();
        
        configManager = new ConfigManager(this);
        
        getServer().getPluginManager().registerEvents(new PlayerEventListener(configManager), this);
        getServer().getPluginManager().registerEvents(new ItemProhibitionListener(configManager), this);
        getServer().getPluginManager().registerEvents(new FarmSaveListener(this), this);
        
        getCommand("analyze").setExecutor(new AnalyzeCommand(configManager));
        getCommand("reloadconfig").setExecutor(new ReloadConfigCommand(this));
        getCommand("rtp").setExecutor(new RTPCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("tpaccept").setExecutor(new TpaAcceptCommand());
        getCommand("tpdeny").setExecutor(new TpaDenyCommand());
        getCommand("tpa").setExecutor(new TpaCommand());
    }
    
}
