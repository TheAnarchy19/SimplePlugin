package com.simpleplugin.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TeleportRequestManager {

    private static final Map<Player, Player> requestMap = new HashMap<>();

    // Añadir una solicitud de teletransportación
    public static void addRequest(Player requester, Player target) {
        requestMap.put(target, requester);
    }

    // Verificar si el jugador tiene una solicitud pendiente
    public static boolean hasRequest(Player player) {
        return requestMap.containsKey(player);
    }

    // Obtener el jugador que ha solicitado la teletransportación
    public static Player getRequester(Player player) {
        return requestMap.get(player);
    }

    // Eliminar la solicitud después de que haya sido aceptada o denegada
    public static void removeRequest(Player player) {
        requestMap.remove(player);
    }
}
