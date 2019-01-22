/**
 * CoordinatesCommand.java 2019-01-21
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands.player;

import org.bukkit.Location;
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
class CoordinatesCommand extends Command {

    public CoordinatesCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    public void execute(CommandSender sender, boolean permission, Player player) {
        if (!super.execute(sender, permission)) return;

        String format = super.mm.getColoredMessage("player.stats.coord");

        Location loc = player.getLocation();
        float    x   = (float) loc.getX();
        float    y   = (float) loc.getY();
        float    z   = (float) loc.getZ();
        float    yaw = loc.getYaw();

        sender.sendMessage(StringUtils.replaceVariables(format, new String[] {
                String.valueOf(x),
                String.valueOf(y),
                String.valueOf(z),
                String.valueOf(yaw)
        }));
    }
}
