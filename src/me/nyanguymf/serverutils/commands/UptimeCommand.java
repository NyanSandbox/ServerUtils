/**
 * UptimeCommand.java 2019-01-20
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.bukkit.command.CommandSender;

import me.nyanguymf.serverutils.managers.MessagesManager;
import me.nyanguymf.serverutils.utils.DateUtils;
import me.nyanguymf.serverutils.utils.StringUtils;

/**
 * @author nyanguymf
 *
 */
public class UptimeCommand extends Command {
    RuntimeMXBean runtimeBean;

    public UptimeCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);

        runtimeBean = ManagementFactory.getRuntimeMXBean();
    }

    @Override
    public boolean execute(CommandSender sender, boolean permission, String... args) {
        if (!super.execute(sender, permission)) return false;

        String message = super.mm.getColoredMessage("uptime");

        sender.sendMessage(StringUtils.replaceVariable(message, getUptime()));

        return true;
    }

    private String getUptime() {
        return DateUtils.parseTime(runtimeBean.getUptime());
    }
}
