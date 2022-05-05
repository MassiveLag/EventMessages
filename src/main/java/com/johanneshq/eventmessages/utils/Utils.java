package com.johanneshq.eventmessages.utils;

import nl.chimpgamer.networkmanager.api.models.player.Player;

public class Utils {

    public static void broadCastMessage(Player player, Messages message) {
        Hook.instance.getAllCachedPlayers().forEach((uuid, onlinePlayer) -> {
            String optionalMessage = Hook.instance.getMessage(onlinePlayer.getLanguage().getId(), message);
            player.sendMessage(optionalMessage
                    .replace("%playername%", player.getName())
                    .replace("%nickmame%", player.getNicknameOrUserName())
                    .replace("%server%", player.getServerDisplay()));
        });
    }

    public static void broadCastServerSwitchMessage(Player player, String oldServer, String newServer, Messages message) {
        Hook.instance.getAllCachedPlayers().forEach((uuid, onlinePlayer) -> {
            String optionalMessage = Hook.instance.getMessage(onlinePlayer.getLanguage().getId(), message);
            player.sendMessage(optionalMessage
                    .replace("%prevserver%", oldServer)
                    .replace("%playername%", player.getName())
                    .replace("%nickmame%", player.getNicknameOrUserName())
                    .replace("%server%", newServer));
        });
    }

}
