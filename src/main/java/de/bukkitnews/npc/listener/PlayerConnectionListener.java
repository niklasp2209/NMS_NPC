package de.bukkitnews.npc.listener;

import de.bukkitnews.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void handleJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        NPC npc = new NPC(player, "niklas", player.getLocation());
    }
}
