package npc;

import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;

public class NPCManager {

    private Map<String, ServerPlayer> npcMap;

    public NPCManager(){
        this.npcMap = new HashMap<>();
    }

    public Map<String, ServerPlayer> getNpcMap() {
        return npcMap;
    }
}
