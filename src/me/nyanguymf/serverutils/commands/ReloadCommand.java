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

    public ReloadCommand(String permission, String command) {
        super(permission, command);
    }

    /**
     * Reloads plugin's messages.
     */
    @Override
    public void execute(CommandSender sender, boolean permission) {
        MessagesManager mm = MessagesManager.getInstance();

        mm.reload();

        sender.sendMessage(mm.getColoredMessage("reload"));
    }

}
