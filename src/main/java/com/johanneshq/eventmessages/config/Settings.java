package com.johanneshq.eventmessages.config;

import com.johanneshq.eventmessages.EventMessages;
import nl.chimpgamer.networkmanager.api.utils.FileUtils;

public class Settings extends FileUtils {
    public Settings(EventMessages eventMessages) {
        super(eventMessages.getDataFolder().getPath(), "settings.yml");
        setupFile(eventMessages.getResource("settings.yml"));
    }

    public void load() {

    }

    @Override
    public void reload() {
        super.reload();

        load();
    }

}
