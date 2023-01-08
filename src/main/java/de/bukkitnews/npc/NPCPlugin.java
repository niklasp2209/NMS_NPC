package de.bukkitnews.npc;

import commands.NPCCommand;
import npc.NPCManager;
import org.bukkit.plugin.java.JavaPlugin;
import utils.CONSTANTS;

public class NPCPlugin extends JavaPlugin {

    private NPCManager npcManager;

    @Override
    public void onEnable(){
        npcManager = new NPCManager();

        CONSTANTS constants = new CONSTANTS();
        initCommands();
    }

    @Override
    public void onDisable(){

    }

    public void initCommands(){
        this.getCommand("npc").setExecutor(new NPCCommand(this));
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }
}
