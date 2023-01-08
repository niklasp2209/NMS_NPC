package de.bukkitnews.npc;

import de.bukkitnews.npc.commands.NPCCommand;
import de.bukkitnews.npc.listener.PlayerConnectionListener;
import de.bukkitnews.npc.npc.NPCManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import de.bukkitnews.npc.utils.ConfigurationUtil;
import de.bukkitnews.npc.utils.Constants;

public class NPCPlugin extends JavaPlugin {

    private NPCManager npcManager;
    private ConfigurationUtil configurationUtil;

    @Override
    public void onEnable(){
        npcManager = new NPCManager(this);
        configurationUtil = new ConfigurationUtil(this);

        Constants constants = new Constants();

        initListener(Bukkit.getPluginManager());
        initCommands();

        npcManager.loadSkins();
    }

    @Override
    public void onDisable(){

    }

    public void initCommands(){
        this.getCommand("npc").setExecutor(new NPCCommand(this));
    }

    public void initListener(PluginManager pluginManager){
        pluginManager.registerEvents(new PlayerConnectionListener(this), this);
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }

    public ConfigurationUtil getConfigurationUtil() {
        return configurationUtil;
    }
}
