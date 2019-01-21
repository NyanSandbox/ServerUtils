/**
 * GCCommand.java 2019-01-10
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
class GCCommand extends Command {

    public GCCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    /**
     * Ask garbage collector: "Please, respectful GC, collect all
     * the garbage".
     */
    @Override
    public boolean execute(CommandSender sender, boolean permissionAll, String... args) {
        if (!super.execute(sender, permissionAll)) return false;

        String message = super.mm.getColoredMessage("gc");

        System.gc();

        sender.sendMessage(message);

        return true;
    }
}
