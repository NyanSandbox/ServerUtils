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

    public MemoryCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    /**
     * Displays memory usage information.
     */
    @Override
    public boolean execute(CommandSender sender, boolean permission, String... args) {
        if (!super.execute(sender, permission)) return false;

        long freeMemory         = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        long availableMemory    = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long maxMemory          = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        long usedMemory         = availableMemory - freeMemory;

        sender.sendMessage(new String[] {
            StringUtils.replaceVarColored(
                    super.mm.getMessage("mem-free"),
                String.valueOf(freeMemory)
            ),
            StringUtils.replaceVarColored(
                    super.mm.getMessage("mem-used"),
                String.valueOf(usedMemory)
            ),
            StringUtils.replaceVarColored(
                    super.mm.getMessage("mem-total"),
                String.valueOf(availableMemory)
            ),
            StringUtils.replaceVarColored(
                    super.mm.getMessage("mem-max"),
                String.valueOf(maxMemory)
            )
        });

        return true;
    }
}
