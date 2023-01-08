package de.bukkitnews.npc.utils;

import de.bukkitnews.npc.NPCPlugin;
import org.bukkit.configuration.Configuration;

public class ConfigurationUtil {

    private final NPCPlugin npcPlugin;
    private final Configuration configuration;

    public ConfigurationUtil(NPCPlugin npcPlugin){
        this.npcPlugin = npcPlugin;
        this.configuration = npcPlugin.getConfig();
    }

    public void setConfigValue(String path, String value){
        Configuration configuration = npcPlugin.getConfig();
        configuration.set(path, value);
        npcPlugin.saveConfig();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
