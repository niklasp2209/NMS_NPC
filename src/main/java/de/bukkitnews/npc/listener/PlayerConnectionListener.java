package de.bukkitnews.npc.listener;

import de.bukkitnews.npc.NPCPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnectionListener implements Listener {

    private NPCPlugin npcPlugin;

    public PlayerConnectionListener(NPCPlugin npcPlugin){
        this.npcPlugin = npcPlugin;
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void handleJoin(PlayerJoinEvent event){
        System.out.println(npcPlugin.getNpcManager().getSkinMap().values());

        npcPlugin.getNpcManager().loadNPC(event.getPlayer());
    }
}
