package de.bukkitnews.npc.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.bukkitnews.npc.NPCPlugin;
import de.bukkitnews.npc.utils.SkinTextureFetcher;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntityPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.function.Consumer;

public class NPC {

    private Player player;
    private String displayname;
    private Location location;
    private final NPCPlugin npcPlugin;

    public NPC(NPCPlugin npcPlugin, Player player, String displayname, Location location){
        this.player = player;
        this.displayname = displayname;
        this.location = location;
        this.npcPlugin = npcPlugin;

        createNPC(player, displayname, location);
    }

    public void createNPC(Player player, String displayname, Location location){
        npcPlugin.getNpcManager().loadSkins();

        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerPlayer serverPlayer = craftPlayer.getHandle();

        MinecraftServer minecraftServer = serverPlayer.getServer();
        ServerLevel serverLevel = serverPlayer.getLevel();

        //GameProfile               || SKIN
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), displayname);
        SkinTextureFetcher.setSkin(gameProfile, displayname, npcPlugin.getNpcManager());

        ServerPlayer npc = new ServerPlayer(minecraftServer, serverLevel, gameProfile);

        //NPC Configuration
        npc.setPos(location.getX(), location.getY(), location.getZ());
        npc.setXRot(location.getPitch());
        npc.setYRot(location.getYaw());

        ServerGamePacketListenerImpl serverGamePacketListener = serverPlayer.connection;

        //PlayerInfoPacket          || Tablist, [..]
        serverGamePacketListener.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));

        //SpawnPlayerPacket         || Visibility
        serverGamePacketListener.send(new ClientboundAddPlayerPacket(npc));


        npcPlugin.getNpcManager().getNpcMap().put(displayname, npc);
    }

    public static void destroyNPC(String displayname){
        for(Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            ServerPlayer serverPlayer = craftPlayer.getHandle();
            ServerGamePacketListenerImpl serverGamePacketListener = serverPlayer.connection;

            ServerPlayer npc = JavaPlugin.getPlugin(NPCPlugin.class).getNpcManager().getNpcMap().get(displayname);

            serverGamePacketListener.send(new ClientboundRemoveEntityPacket(npc.getId()));
            

        }
    }
}
