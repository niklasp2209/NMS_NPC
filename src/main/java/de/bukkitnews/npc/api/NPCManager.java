package de.bukkitnews.npc.api;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPCManager {

    public static void createNPC(Player player, String displayname){

        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerPlayer serverPlayer = craftPlayer.getHandle();

        MinecraftServer minecraftServer = serverPlayer.getServer();
        ServerLevel serverLevel = serverPlayer.getLevel();

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), displayname);

        ServerPlayer npc = new ServerPlayer(minecraftServer, serverLevel, gameProfile);

        ServerGamePacketListenerImpl serverGamePacketListener = serverPlayer.connection;

        //PlayerInfoPacket          || Tablist, [..]
        serverGamePacketListener.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));

        //SpawnPlayerPacket         || Visibility
        serverGamePacketListener.send(new ClientboundAddPlayerPacket(serverPlayer));
    }
}
