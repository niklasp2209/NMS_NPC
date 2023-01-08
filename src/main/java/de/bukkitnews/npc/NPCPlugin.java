package de.bukkitnews.npc;

import commands.NPCCommand;
import org.bukkit.plugin.java.JavaPlugin;
import utils.CONSTANTS;

public class NPCPlugin extends JavaPlugin {

    @Override
    public void onEnable(){

        CONSTANTS constants = new CONSTANTS();
        initCommands();
    }

    @Override
    public void onDisable(){

    }

    public void initCommands(){
        this.getCommand("npc").setExecutor(new NPCCommand(this));
    }
}
