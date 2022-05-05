package com.johanneshq.eventmessages;

import com.johanneshq.eventmessages.config.Settings;
import com.johanneshq.eventmessages.listeners.BungeePlayerListener;
import com.johanneshq.eventmessages.listeners.NMPlayerListener;
import com.johanneshq.eventmessages.listeners.VelocityPlayerListener;
import com.johanneshq.eventmessages.utils.Hook;
import nl.chimpgamer.networkmanager.api.event.events.player.AsyncPlayerLoginEvent;
import nl.chimpgamer.networkmanager.api.event.events.player.PlayerDisconnectEvent;
import nl.chimpgamer.networkmanager.api.extensions.NMExtension;
import nl.chimpgamer.networkmanager.api.utils.PlatformType;

public class EventMessages extends NMExtension {

    public static EventMessages eventMessages;

    public Settings settings = new Settings(this);

    @Override
    protected void onConfigsReload() {
        settings.reload();
    }

    @Override
    protected void onDisable() {

    }

    @Override
    protected void onEnable() {
        eventMessages = this;
        if (!networkManagerPlugin.getPlatformType().isProxy()) {
            getLogger().severe("This extension only works for BungeeCord");
            return;
        }

        settings.reload();
        new Hook();

        NMPlayerListener playerListener = new NMPlayerListener();
        eventMessages.getNetworkManager().getEventBus().subscribe(AsyncPlayerLoginEvent.class, playerListener::onLogin);
        eventMessages.getNetworkManager().getEventBus().subscribe(PlayerDisconnectEvent.class, playerListener::onLogout);

        if (networkManagerPlugin.getPlatformType() == PlatformType.BUNGEECORD) {
            getNetworkManager().registerListener(new BungeePlayerListener());
        } else if (networkManagerPlugin.getPlatformType() == PlatformType.VELOCITY) {
            getNetworkManager().registerListener(new VelocityPlayerListener());
        }
    }
}
