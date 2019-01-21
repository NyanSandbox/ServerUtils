/**
 * TimeCommand.java 2019-01-20
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

import org.bukkit.command.CommandSender;

import me.nyanguymf.serverutils.managers.MessagesManager;

/**
 * @author nyanguymf
 *
 */
class TimeCommand extends Command {

    public TimeCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    @Override
    public boolean execute(CommandSender sender, boolean permission, String... args) {
        if (!super.execute(sender, permission)) return false;

        return true;
    }
}
