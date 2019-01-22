/**
 * GCCommand.java 2019-01-10
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import me.nyanguymf.serverutils.ServerUtils;
import me.nyanguymf.serverutils.managers.MessagesManager;

/**
 * @author nyanguymf
 *
 */
class GCCommand extends Command {
    private ServerUtils plugin;

    public GCCommand(String permission, String command, ServerUtils plugin, MessagesManager mm) {
        super(permission, command, mm);

        this.plugin = plugin;
    }

    /**
     * Ask garbage collector: "Please, respectful GC, collect all
     * the garbage".
     */
    @Override
    public boolean execute(CommandSender sender, boolean permissionAll, String... args) {
        if (!super.execute(sender, permissionAll)) return false;

        String message = super.mm.getColoredMessage("gc");

        new BukkitRunnable() {
            public void run() {
                System.gc();
                sender.sendMessage(message);
            }
        }.runTaskAsynchronously(plugin);

        return true;
    }
}
