package utils;

public class CONSTANTS {

    public static String prefix,
                   noPermission;

    public static String perm_NPCCommand;

    public static String message_NPCExists,
                         message_CreatedNPC,
                         message_NPCNotExists,
                         message_NPCDeleted;

    public CONSTANTS(){
        prefix = "§7[§eNPC§7] §r";
        noPermission = prefix+"§cDu hast leider nicht genügend Rechte!";

        perm_NPCCommand = "npc.command.npc";

        message_NPCExists = prefix+"§cDieser NPC existiert bereits!";
        message_CreatedNPC = prefix+"§7Du hast §e%s §7erfolgreich §aerstellt§7.";
        message_NPCNotExists = prefix+"§e%s §cexistiert nicht.";
        message_NPCDeleted = prefix+"§7Du hast §e%s §cgelöscht§7.";
    }
}
