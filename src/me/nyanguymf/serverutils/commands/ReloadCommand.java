/**
 * ReloadCommand.java 2019-01-11
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
class ReloadCommand extends Command {

    public ReloadCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    /**
     * Reloads plugin's messages.
     */
    @Override
    public boolean execute(CommandSender sender, boolean permission, String... args) {
        if (!super.execute(sender, permission)) return false;
        mm.reload();

        sender.sendMessage(mm.getColoredMessage("reload"));

        return true;
    }

}
