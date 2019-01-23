/**
 * TimeCommand.java 2019-01-20
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

import java.util.Calendar;
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

    @Override
    public boolean execute(CommandSender sender, boolean permission, String... args) {
        if (!super.execute(sender, permission)) return false;

        String format = super.mm.getColoredMessage("system-time");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int year = cal.get(Calendar.YEAR);
        int mon  = cal.get(Calendar.MONTH) + 1;
        int day  = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min  = cal.get(Calendar.MINUTE);
        int sec  = cal.get(Calendar.SECOND);
        int mill = cal.get(Calendar.MILLISECOND);

        String message = parseDate(format, year, mon, day, hour, min, sec, mill);

        sender.sendMessage(message);

        return true;
    }

    private String parseDate(
            String format, int year, int mon,
            int day, int hour, int min,
            int sec, int mill
            ) {
        String parsed = format;

        parsed = parsed.replace("{year}", String.valueOf(year));
        parsed = parsed.replace("{month}", String.valueOf(mon));
        parsed = parsed.replace("{day}", String.valueOf(day));
        parsed = parsed.replace("{hour}", String.valueOf(hour));
        parsed = parsed.replace("{min}", String.valueOf(min));
        parsed = parsed.replace("{sec}", String.valueOf(sec));
        parsed = parsed.replace("{mill}", String.valueOf(mill));
        
        return parsed;
    }
}
