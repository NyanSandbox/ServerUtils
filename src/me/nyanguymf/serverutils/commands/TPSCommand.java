/**
 * TPSCommand.java 2019-01-09
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.nyanguymf.serverutils.ServerUtils;
import me.nyanguymf.serverutils.managers.MessagesManager;
import me.nyanguymf.serverutils.utils.StringUtils;

/**
 * @author nyanguymf
 *
 * @author DemmyDemon https://github.com/DemmyDemon/LagMeter
 */
public class TPSCommand extends Command {
    protected static    float           ticksPerSecond  = 20;
    protected static    LagMeterStack   history         = new LagMeterStack();
    protected static    boolean         useAverage      = true;
    private   static    ServerUtils     su              = ServerUtils.getInstance();
    protected           LagMeterPoller  poller          = new LagMeterPoller();
    protected           int             averageLength   = 10;
    protected           int             interval        = 40;

    /** init scheduler for TPS */
    public TPSCommand(String permission, String command) {
        super(permission, command);

        history.setMaxSize(averageLength);
        su.getServer().getScheduler().scheduleSyncRepeatingTask(su, poller, 0, interval);
    }

    /**
     * Shows TPS to command sender.
     *
     * A lot acknowledgements to DemmyDemon
     * for open source project. Origin
     * https://github.com/DemmyDemon/LagMeter
     */
    @Override
    public void execute(CommandSender sender, boolean permissionAll) {
        if (!sender.hasPermission(super.permission) && !permissionAll) {
            super.sendNoPermission(sender);
            return;
        }

        MessagesManager mm      = MessagesManager.getInstance();
        String          tps     = getTPS();
        String          message = mm.getMessage("tps");

        sender.sendMessage(StringUtils.replaceVarColored(message, tps));
    }

    private static String getTPS() {
        String  lagMeter    = "";
        float   tps         = 0f;

        if (useAverage)
            tps = history.getAverage();
        else
            tps = ticksPerSecond;

        if (tps < 21) {
            int looped = 0;

            while (looped++ < tps) {
                lagMeter += "#";
            }

            lagMeter += ChatColor.WHITE;

            while (looped++ <= 20)
                lagMeter += "_";

        } else {
            return "TPS meter just loaded, please wait for polling.";
        }

        ChatColor color;

        if (tps >= 20)
            color = ChatColor.GREEN;
        else if (tps >= 18)
            color = ChatColor.GREEN;
        else if (tps >= 15)
            color = ChatColor.YELLOW;
        else
            color = ChatColor.RED;

        return ChatColor.AQUA + "[" + color + lagMeter + ChatColor.AQUA + "] " + tps;
    }
}
