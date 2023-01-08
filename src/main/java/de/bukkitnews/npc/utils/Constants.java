package de.bukkitnews.npc.utils;

public class Constants {

    public static final String PREFIX = "§7[§eNPC§7] §r",
                               NOPERMISSION = PREFIX+"§cDu hast leider nicht genügend Rechte!";

    public static String PERM_NPCCOMMAND;

    public static String MESSAGE_NPCEXISTS,
                         MESSAGE_CREATEDNPC,
                         MESSAGE_NPCNOTEXISTS,
                         MESSAGE_NPCDELETED;

    public Constants(){
        PERM_NPCCOMMAND = "npc.command.npc";

        MESSAGE_NPCEXISTS = PREFIX+"§cDieser NPC existiert bereits!";
        MESSAGE_CREATEDNPC = PREFIX+"§7Du hast §e%s §7erfolgreich §aerstellt§7.";
        MESSAGE_NPCNOTEXISTS = PREFIX+"§e%s §cexistiert nicht.";
        MESSAGE_NPCDELETED = PREFIX+"§7Du hast §e%s §cgelöscht§7.";
    }
}
