package npc;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPC {

    private Player player;
    private String displayname;
    private Location location;

    public NPC(Player player, String displayname, Location location){
        this.player = player;
        this.displayname = displayname;
        this.location = location;

        createNPC(player, displayname, location);
    }

    public void createNPC(Player player, String displayname, Location location){
        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerPlayer serverPlayer = craftPlayer.getHandle();

        MinecraftServer minecraftServer = serverPlayer.getServer();
        ServerLevel serverLevel = serverPlayer.getLevel();

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), displayname);

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
    }

    public void destroyNPC(){
        
    }
}
