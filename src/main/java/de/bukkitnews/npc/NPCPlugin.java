package de.bukkitnews.npc;

import de.bukkitnews.npc.commands.NPCCommand;
import de.bukkitnews.npc.npc.NPCManager;
import org.bukkit.plugin.java.JavaPlugin;
import de.bukkitnews.npc.utils.ConfigurationUtil;
import de.bukkitnews.npc.utils.Constants;

public class NPCPlugin extends JavaPlugin {

    private NPCManager npcManager;
    private ConfigurationUtil configurationUtil;

    @Override
    public void onEnable(){
        npcManager = new NPCManager();
        configurationUtil = new ConfigurationUtil(this);

        Constants constants = new Constants();
        initCommands();
    }

    @Override
    public void onDisable(){

    }

    public void initCommands(){
        this.getCommand("de/bukkitnews/npc/npc").setExecutor(new NPCCommand(this));
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }

    public ConfigurationUtil getConfigurationUtil() {
        return configurationUtil;
    }
}
