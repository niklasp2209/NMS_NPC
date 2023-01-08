package de.bukkitnews.npc.utils;

import de.bukkitnews.npc.NPCPlugin;
import org.bukkit.configuration.Configuration;

public class ConfigurationUtil {

    private final NPCPlugin npcPlugin;

    public ConfigurationUtil(NPCPlugin npcPlugin){
        this.npcPlugin = npcPlugin;
    }

    public void setConfigValue(String path, String value){
        Configuration configuration = npcPlugin.getConfig();
        configuration.set(path, value);
        npcPlugin.saveConfig();
    }
}
