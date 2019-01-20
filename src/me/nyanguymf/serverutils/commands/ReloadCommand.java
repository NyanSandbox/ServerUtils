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
    private MessagesManager mm;

    public ReloadCommand(String permission, String command, MessagesManager mm) {
        super(permission, command);

        this.mm = mm;
    }

    /**
     * Reloads plugin's messages.
     */
    @Override
    public void execute(CommandSender sender, boolean permission) {
        mm.reload();

        sender.sendMessage(mm.getColoredMessage("reload"));
    }

}
