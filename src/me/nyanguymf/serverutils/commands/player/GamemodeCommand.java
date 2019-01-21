/**
 * GamemodeCommand.java 2019-01-21
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands.player;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nyanguymf.serverutils.commands.Command;
import me.nyanguymf.serverutils.managers.MessagesManager;
import me.nyanguymf.serverutils.utils.StringUtils;

/**
 * @author nyanguymf
 *
 * @since 1.2
 */
class GamemodeCommand extends Command {

    public GamemodeCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    public void execute(CommandSender sender, boolean permission, Player player) {
        if (!super.execute(sender, permission)) return;

        String format = super.mm.getMessage("player.stats.gamemode");
        String gamemode;

        switch (player.getGameMode()) {
        case CREATIVE:
            gamemode = super.mm.getMessage("player.words.creative");
            break;
        case SURVIVAL:
            gamemode = super.mm.getMessage("player.words.survival");
            break;
        case SPECTATOR:
            gamemode = super.mm.getMessage("player.words.spectator");
            break;
        case ADVENTURE:
            gamemode = super.mm.getMessage("player.words.adventure");
            break;

        default:
            gamemode = ChatColor.DARK_RED + "invalid";
            System.err.println("Get invalid GameMode: " + player.getGameMode().toString());
            break;
        }

        sender.sendMessage(StringUtils.replaceVarColored(format, gamemode));
    }
}
