package com.simpleplugin.utils;

import org.bukkit.World;

public class PerformanceUtils {

    public static int getLoadedChunks(World world) {
        return world.getLoadedChunks().length;
    }
}
