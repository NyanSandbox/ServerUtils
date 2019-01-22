/**
 * HpCommand.java 2019-01-21
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
class HpCommand extends Command {
    public HpCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    public void execute(CommandSender sender, boolean permission, Player player) {
        if (!super.execute(sender, permission)) return;

        String health;
        String hpMessage = super.mm.getColoredMessage("player.stats.hp");

        int hp = (int) player.getHealth();

        if (hp >= 18)
            health = ChatColor.GREEN + "" + hp;
        else if (hp >= 15)
            health = ChatColor.YELLOW + "" + hp;
        else if (hp >= 10)
            health = ChatColor.GOLD + "" + hp;
        else if (hp >= 5)
            health = ChatColor.RED + "" + hp;
        else
            health = ChatColor.DARK_RED + "" + hp;

        sender.sendMessage(StringUtils.replaceVariable(hpMessage, health));
    }
}
