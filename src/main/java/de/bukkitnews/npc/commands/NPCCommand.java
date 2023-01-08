package de.bukkitnews.npc.commands;

import de.bukkitnews.npc.npc.NPC;
import de.bukkitnews.npc.NPCPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import de.bukkitnews.npc.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class NPCCommand implements CommandExecutor {

    /*
    /NPC CREATE <NAME>
    /NPC DELETE <NAME>
    /NPC SKIN <NAME> <URL>
     */

    private NPCPlugin npcPlugin;
    private Configuration configuration;

    public NPCCommand(NPCPlugin npcPlugin){
        this.npcPlugin = npcPlugin;
        this.configuration = npcPlugin.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(player.hasPermission(Constants.PERM_NPCCOMMAND)){
                if(args.length == 2){
                    if(args[0].equalsIgnoreCase("create")){
                        String name = args[1];
                        if(configuration.getString(".NPC"+"."+name) != null)
                            player.sendMessage(Constants.MESSAGE_NPCEXISTS);
                        else{
                            npcPlugin.getConfigurationUtil().setConfigValue(".NPC"+"."+name+".Displayname", name);
                            npcPlugin.getConfigurationUtil().setConfigValue(".NPC"+"."+name+".World", player.getLocation().getWorld().getName());
                            npcPlugin.getConfigurationUtil().setConfigValue(".NPC"+"."+name+".X", player.getLocation().getX());
                            npcPlugin.getConfigurationUtil().setConfigValue(".NPC"+"."+name+".Y", player.getLocation().getY());
                            npcPlugin.getConfigurationUtil().setConfigValue(".NPC"+"."+name+".Z", player.getLocation().getZ());
                            npcPlugin.getConfigurationUtil().setConfigValue(".NPC"+"."+name+".Yaw", player.getLocation().getYaw());
                            npcPlugin.getConfigurationUtil().setConfigValue(".NPC"+"."+name+".Pitch", player.getLocation().getPitch());
                            npcPlugin.getConfigurationUtil().setConfigValue(".NPC"+"."+name+".Skin", name);
                            NPC npc = new NPC(npcPlugin, player, name, player.getLocation());
                            player.sendMessage(String.format(Constants.MESSAGE_CREATEDNPC, name));
                        }
                    }else if(args[0].equalsIgnoreCase("delete")){
                        String name = args[1];
                        if(configuration.getString(".NPC"+"."+name) != null){
                            npcPlugin.getConfigurationUtil().setConfigValue(".NPC"+"."+name, null);
                            NPC.destroyNPC(name);
                            player.sendMessage(String.format(Constants.MESSAGE_NPCDELETED, name));
                        }else
                            player.sendMessage(String.format(Constants.MESSAGE_NPCNOTEXISTS, name));
                    }
                }
            }else
                player.sendMessage(Constants.NOPERMISSION);
        }else
            commandSender.sendMessage("§cDafür musst du ein Spieler sein.");
        return false;
    }
}
