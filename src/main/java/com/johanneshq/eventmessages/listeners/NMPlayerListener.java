package com.johanneshq.eventmessages.listeners;

import com.johanneshq.eventmessages.EventMessages;
import com.johanneshq.eventmessages.utils.Messages;
import com.johanneshq.eventmessages.utils.Utils;
import nl.chimpgamer.networkmanager.api.event.events.player.AsyncPlayerLoginEvent;
import nl.chimpgamer.networkmanager.api.event.events.player.PlayerDisconnectEvent;

public class NMPlayerListener {

    public void onLogin(AsyncPlayerLoginEvent event) {
        if (EventMessages.eventMessages.settings.booleanMap.get("notifyJoin"))
            Utils.broadCastMessage(event.getPlayer(), Messages.LOGIN);
    }

    public void onLogout(PlayerDisconnectEvent event) {
        if (EventMessages.eventMessages.settings.booleanMap.get("notifyQuit"))
            Utils.broadCastMessage(event.getPlayer(), Messages.QUIT);
    }
}
