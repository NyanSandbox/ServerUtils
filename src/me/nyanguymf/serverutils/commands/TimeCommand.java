/**
 * TimeCommand.java 2019-01-20
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

import java.util.Date;

import org.bukkit.command.CommandSender;

import me.nyanguymf.serverutils.managers.MessagesManager;

/**
 * @author nyanguymf
 *
 * @since 1.2
 */
class TimeCommand extends Command {

    public TimeCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean execute(CommandSender sender, boolean permission, String... args) {
        if (!super.execute(sender, permission)) return false;

        String format = super.mm.getColoredMessage("system-time");
        Date date = new Date(System.currentTimeMillis());
        int  year = date.getYear();
        int  mon  = date.getMonth();
        int  day  = date.getDay();
        int  hour = date.getHours();
        int  min  = date.getMinutes();
        int  sec  = date.getSeconds();

        String message = parseDate(format, year, mon, day, hour, min, sec);

        sender.sendMessage(message);

        return true;
    }

    private String parseDate(
            String format, int year, int mon,
            int day, int hour, int min,
            int sec
            ) {
        String parsed = format;

        parsed = parsed.replace("{year}", String.valueOf(year));
        parsed = parsed.replace("{month}", String.valueOf(mon));
        parsed = parsed.replace("{day}", String.valueOf(day));
        parsed = parsed.replace("{hour}", String.valueOf(hour));
        parsed = parsed.replace("{min}", String.valueOf(min));
        parsed = parsed.replace("{sec}", String.valueOf(sec));
        
        return parsed;
    }
}
