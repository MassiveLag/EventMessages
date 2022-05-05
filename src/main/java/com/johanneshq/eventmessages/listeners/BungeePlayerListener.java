package com.johanneshq.eventmessages.listeners;

import com.johanneshq.eventmessages.EventMessages;
import com.johanneshq.eventmessages.utils.Hook;
import com.johanneshq.eventmessages.utils.Messages;
import com.johanneshq.eventmessages.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import nl.chimpgamer.networkmanager.api.models.servers.Server;

import java.util.Optional;

public class BungeePlayerListener implements Listener {

    @EventHandler
    public void onSwitch(ServerSwitchEvent event) {
        if (event.getFrom() == null)
            return;

        if (!EventMessages.eventMessages.settings.getBoolean("settings.notifyServerSwitch"))
            return;

        ProxiedPlayer proxiedPlayer = event.getPlayer();

        String from = event.getFrom().getName();
        String to = event.getPlayer().getServer().getInfo().getName();

        Optional<Server> optionalFromServer = Hook.instance.getServer(from);
        Optional<Server> optionalToServer = Hook.instance.getServer(to);

        if (optionalFromServer.isPresent())
            from = optionalFromServer.get().getDisplayName();

        if (optionalToServer.isPresent())
            to = optionalToServer.get().getDisplayName();

        String finalTo = to;
        String finalFrom = from;
        Hook.instance.getPlayer(proxiedPlayer.getUniqueId()).ifPresent(player -> {
            Utils.broadCastServerSwitchMessage(player, finalFrom, finalTo, Messages.SERVER_SWITCH);
        });
    }
}
