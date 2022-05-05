package com.johanneshq.eventmessages.utils;

public enum Messages {

    LOGIN("eventmessages.login", "%playername% joined the network!", "0.1", "eventmessages"),
    QUIT("eventmessages.logout", "%playername% left the network!", "0.1", "eventmessages"),
    SERVER_SWITCH("eventmessages.serverswitch", "%playername% switched from %prevserver% to %server% server!", "0.1", "eventmessages");

    final String key;
    final String message;
    final String version;
    final String pluginName;

    Messages(String key, String message, String version, String pluginName) {
        this.key = key;
        this.message = message;
        this.version = version;
        this.pluginName = pluginName;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public String getVersion() {
        return version;
    }

    public String getPluginName() {
        return pluginName;
    }

}
