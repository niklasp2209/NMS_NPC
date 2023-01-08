package de.bukkitnews.npc.npc;

import de.bukkitnews.npc.NPCPlugin;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class NPCManager {

    private Map<String, ServerPlayer> npcMap;
    private NPCPlugin npcPlugin;

    public NPCManager(NPCPlugin npcPlugin){
        this.npcMap = new HashMap<>();
        this.npcPlugin = npcPlugin;
    }

    public Map<String, ServerPlayer> getNpcMap() {
        return npcMap;
    }

    public void loadNPC(Player player){
        Configuration configuration = npcPlugin.getConfig();
        for(String path : configuration.getConfigurationSection(".NPC").getKeys(false)){
            String displayname = configuration.getString(".NPC."+path+".Displayname");
            String worldName = configuration.getString(".NPC."+path+".World");
            double x = configuration.getInt(".NPC."+path+".X");
            double y = configuration.getInt(".NPC."+path+".Y");
            double z = configuration.getInt(".NPC."+path+".Z");
            float yaw = configuration.getInt(".NPC."+path+".Yaw");
            float pitch = configuration.getInt(".NPC."+path+".Pitch");
            Location location = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);

            System.out.println(x);

            NPC npc = new NPC(npcPlugin, player, displayname, location);
        }
    }
}
