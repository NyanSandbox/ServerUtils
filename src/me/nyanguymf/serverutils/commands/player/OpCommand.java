/**
 * OpCommand.java 2019-01-21
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
class OpCommand extends Command {

    public OpCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    public void execute(CommandSender sender, boolean permission, Player player) {
        if (!super.execute(sender, permission)) return;

        String format = super.mm.getMessage("player.stats.op");
        String isOp;

        if (player.isOp())
            isOp = super.mm.getMessage("player.words.true");
        else
            isOp = super.mm.getMessage("player.words.false");

        sender.sendMessage(StringUtils.replaceVarColored(format, isOp));
    }
}
