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
public abstract class Command {
    /**
     * Command's permission to launch command.
     */
    protected String permission;

    protected String command;

    protected MessagesManager mm;

    /**
     * Creates new command with given permission.
     */
    public Command(String permission, String command, MessagesManager mm) {
        this.permission = permission;
        this.command    = command;
        this.mm         = mm;
    }

    /**
     * Executes command.
     *
     * Check player's permission.
     *
     * @param sender Command sender.
     *
     * @param permission Does the sender have all permissions.
     *
     * @param args Unnecessary command arguments depend on implementation.
     * @return TODO
     */
    protected boolean execute(CommandSender sender, boolean permission, String... args) {
        if (!sender.hasPermission(this.permission) && !permission) {
            this.sendNoPermission(sender);
            return false;
        }

        return true;
    }

    /**
     * Tells to command sender that he has no
     * permission to perform some command.
     *
     * @param reciever Who will receive message.
     *
     * @param cmd Command not authorized to perform.
     */
    protected void sendNoPermission(CommandSender reciever) {
        String format   = mm.getColoredMessage("no-permission");
        String message  = StringUtils.replaceVarColored(format, command);

        reciever.sendMessage(message);
    }
}
