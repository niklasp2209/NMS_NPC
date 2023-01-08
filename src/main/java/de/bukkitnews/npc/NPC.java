package de.bukkitnews.npc;

import de.bukkitnews.npc.listener.PlayerConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NPC extends JavaPlugin {

    @Override
    public void onEnable(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerConnectionListener(), this);
    }

    @Override
    public void onDisable(){

    }
}
