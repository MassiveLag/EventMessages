package com.johanneshq.eventmessages.config;

import com.johanneshq.eventmessages.EventMessages;
import nl.chimpgamer.networkmanager.api.utils.FileUtils;

import java.util.HashMap;
import java.util.Map;

public class Settings extends FileUtils {
    public Settings(EventMessages eventMessages) {
        super(eventMessages.getDataFolder().getPath(), "settings.yml");
    }

    public Map<String, Boolean> booleanMap = new HashMap<>();

    public void load() {
        for (String settings : getConfig().getConfigurationSection("settings").getKeys(false)) {
            System.out.println("Found setting key: " + settings);
            booleanMap.put(settings, getBoolean(settings));
        }
    }

    @Override
    public void reload() {
        super.reload();
        booleanMap.clear();
        load();
    }
}
