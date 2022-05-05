package com.johanneshq.eventmessages.utils;

import com.johanneshq.eventmessages.EventMessages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.scheduler.TaskScheduler;
import nl.chimpgamer.networkmanager.api.NetworkManagerPlugin;
import nl.chimpgamer.networkmanager.api.NetworkManagerProvider;
import nl.chimpgamer.networkmanager.api.Scheduler;
import nl.chimpgamer.networkmanager.api.models.player.Player;
import nl.chimpgamer.networkmanager.api.utils.TextUtils;

import java.util.concurrent.CompletableFuture;

public class Utils {

    /**
     *
     * @param player the network player
     * @param message the message
     */

    public static void broadCastMessage(Player player, Messages message) {
        System.out.println("broadCastMessage = " + message.getKey());
        System.out.println("playerSize = " + Hook.instance.getAllCachedPlayers().size());
        EventMessages.eventMessages.getScheduler().runDelayed(() -> Hook.instance.getAllCachedPlayers().forEach((uuid, onlinePlayer) -> {
            System.out.println(uuid);
            String optionalMessage = Hook.instance.getMessage(onlinePlayer.getLanguage().getId(), message);
            TextComponent text = Component.text(optionalMessage
                    .replace("%playername%", player.getName())
                    .replace("%displayname%", player.getDisplayName())
                    .replace("%serverdisplay%", TextUtils.toLegacy(player.getServerDisplay())));
            System.out.println("Sending message to player= " + onlinePlayer.getName());
            onlinePlayer.sendMessage(text);
        }), 25);
    }

    /**
     *
     * @param player the network player
     * @param oldServer the previous server
     * @param newServer the new current server
     * @param message the message
     */
    public static void broadCastServerSwitchMessage(Player player, String oldServer, String newServer, Messages message) {
        Hook.instance.getAllCachedPlayers().forEach((uuid, onlinePlayer) -> {
            String optionalMessage = Hook.instance.getMessage(onlinePlayer.getLanguage().getId(), message);
            TextComponent text = Component.text(optionalMessage
                    .replace("%prevserver%", oldServer)
                    .replace("%playername%", player.getName())
                    .replace("%displayname%", player.getDisplayName())
                    .replace("%server%", newServer));

            onlinePlayer.sendMessage(text);
        });
    }

}
