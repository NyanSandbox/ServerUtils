/**
 * MemoryCommand.java 2019-01-11
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
class MemoryCommand extends Command {
    private MessagesManager mm;

    public MemoryCommand(String permission, String command, MessagesManager mm) {
        super(permission, command);

        this.mm = mm;
    }

    /**
     * Displays memory usage information.
     */
    @Override
    public void execute(CommandSender sender, boolean permission) {
        if (!sender.hasPermission(super.permission)) {
            super.sendNoPermission(sender, mm.getColoredMessage("no-permission"));
            return;
        }

        long freeMemory         = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        long availableMemory    = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long maxMemory          = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        long usedMemory         = availableMemory - freeMemory;

        sender.sendMessage(new String[] {
            StringUtils.replaceVarColored(
                mm.getMessage("mem-free"),
                String.valueOf(freeMemory)
            ),
            StringUtils.replaceVarColored(
                mm.getMessage("mem-used"),
                String.valueOf(usedMemory)
            ),
            StringUtils.replaceVarColored(
                mm.getMessage("mem-total"),
                String.valueOf(availableMemory)
            ),
            StringUtils.replaceVarColored(
                mm.getMessage("mem-max"),
                String.valueOf(maxMemory)
            )
        });
    }
}
