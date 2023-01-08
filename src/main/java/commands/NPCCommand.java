package commands;

import npc.NPC;
import de.bukkitnews.npc.NPCPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import utils.CONSTANTS;

import java.util.HashMap;
import java.util.Map;

public class NPCCommand implements CommandExecutor {

    /*
    /NPC CREATE <NAME>
     */

    private NPCPlugin npcPlugin;
    private Map<Player, Integer> selectedNPC;
    private Configuration configuration;

    public NPCCommand(NPCPlugin npcPlugin){
        this.npcPlugin = npcPlugin;
        this.selectedNPC = new HashMap<>();
        this.configuration = npcPlugin.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(player.hasPermission(CONSTANTS.perm_NPCCommand)){
                if(args.length == 2){
                    if(args[0].equalsIgnoreCase("create")){
                        if(configuration.getString(".NPC"+"."+args[1]) != null)
                            player.sendMessage(CONSTANTS.message_NPCExists);
                        else{
                            String name = args[1];
                            configuration.set(".NPC"+"."+name+".Displayname", name);
                            configuration.set(".NPC"+"."+name+".Skin", "Default");
                            npcPlugin.saveConfig();
                            new NPC(player, name, player.getLocation());
                            player.sendMessage(String.format(CONSTANTS.message_CreatedNPC, name));
                        }
                    }else if(args[0].equalsIgnoreCase("delete")){
                        
                    }
                }
            }else
                player.sendMessage(CONSTANTS.noPermission);
        }else
            commandSender.sendMessage("§cDafür musst du ein Spieler sein.");
        return false;
    }
}
