package com.johanneshq.eventmessages.listeners;

import com.johanneshq.eventmessages.EventMessages;
import com.johanneshq.eventmessages.utils.Messages;
import com.johanneshq.eventmessages.utils.Hook;
import com.johanneshq.eventmessages.utils.Utils;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import nl.chimpgamer.networkmanager.api.models.servers.Server;

import java.util.Optional;

public class VelocityPlayerListener {

    @Subscribe
    public void onServerSwitch(ServerConnectedEvent event) {
        if (!EventMessages.eventMessages.settings.getBoolean("settings.notifyServerSwitch"))
            return;

        if (event.getPreviousServer().isEmpty())
            return;

        String from = event.getPreviousServer().get().getServerInfo().getName();
        String to = event.getServer().getServerInfo().getName();

        Optional<Server> optionalFromServer = Hook.instance.getServer(from);
        Optional<Server> optionalToServer = Hook.instance.getServer(to);

        if (optionalFromServer.isPresent())
            from = optionalFromServer.get().getDisplayName();

        if (optionalToServer.isPresent())
            to = optionalToServer.get().getDisplayName();

        String finalTo = to;
        String finalFrom = from;
        Hook.instance.getPlayer(event.getPlayer().getUniqueId()).ifPresent(player -> {
            Utils.broadCastServerSwitchMessage(player, finalFrom, finalTo, Messages.SERVER_SWITCH);
        });
    }

}
