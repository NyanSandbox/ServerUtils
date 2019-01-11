/**
 * Command.java 2019-01-11
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

import org.bukkit.command.CommandSender;

import me.nyanguymf.serverutils.managers.MessagesManager;
import me.nyanguymf.serverutils.utils.StringUtils;

/**
 * @author nyanguymf
 *
 */
abstract class Command {
    /**
     * Command's permission to launch command.
     */
    protected String permission;

    protected String command;

    /**
     * Creates new command with given permission.
     */
    public Command(String permission, String command) {
        this.permission = permission;
        this.command    = command;
    }

    /**
     * Executes command.
     *
     * @param sender Command sender.
     *
     * @param permission Does the sender have all permissions.
     */
    public abstract void execute(CommandSender sender, boolean permission);

    /**
     * Tells to command sender that he has no
     * permission to perform some command.
     *
     * @param reciever Who will receive message.
     *
     * @param cmd Command not authorized to perform.
     */
    protected void sendNoPermission(CommandSender reciever) {
        String format   = MessagesManager.getInstance().getColoredMessage("no-permission");
        String message  = StringUtils.replaceVarColored(format, command);

        reciever.sendMessage(message);
    }
}
