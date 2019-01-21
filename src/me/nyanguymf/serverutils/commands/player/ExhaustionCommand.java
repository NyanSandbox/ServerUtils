/**
 * ExhaustionCommand.java 2019-01-21
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands.player;

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
class ExhaustionCommand extends Command {

    public ExhaustionCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    public void execute(CommandSender sender, boolean permission, Player player) {
        if (!super.execute(sender, permission)) return;

        String format     = super.mm.getColoredMessage("player.stats.exhaustion");
        String exhaustion = String.valueOf(player.getExhaustion());

        sender.sendMessage(StringUtils.replaceVariable(format, exhaustion));
    }
}
