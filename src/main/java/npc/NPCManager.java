package npc;

import java.util.HashMap;
import java.util.Map;

public class NPCManager {

    private Map<String, NPC> npcMap;

    public NPCManager(){
        this.npcMap = new HashMap<>();
    }

    public Map<String, NPC> getNpcMap() {
        return npcMap;
    }
}
