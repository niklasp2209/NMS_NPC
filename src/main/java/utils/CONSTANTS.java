package utils;

public class CONSTANTS {

    public static String prefix,
                   noPermission;

    public static String perm_NPCCommand;

    public static String message_NPCExists,
                         message_CreatedNPC;

    public CONSTANTS(){
        prefix = "§7[§eNPC§7] §r";
        noPermission = prefix+"§cDu hast leider nicht genügend Rechte!";

        perm_NPCCommand = "npc.command.npc";

        message_NPCExists = prefix+"§cDieser NPC existiert bereits!";
        message_CreatedNPC = prefix+"§7Du hast §e%s §7erfolgreich §aerstellt§7.";
    }
}
