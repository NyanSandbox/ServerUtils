/**
 * SUCommand.java 2019-01-09
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.nyanguymf.serverutils.ServerUtils;

/**
 * @author nyanguymf
 *
 */
public class SUCommand implements CommandExecutor {
    private MobsCommand     mobs;
    private GCCommand       gc;
    private TPSCommand      tps;
    private MemoryCommand   mem;
    private ReloadCommand   reload;

    public SUCommand() {
        mobs    = new MobsCommand("serverutils.mobs", "su mobs");
        gc      = new GCCommand("serverutils.gc", "su gc");
        tps     = ServerUtils.getTPSCommand();
        mem     = new MemoryCommand("serverutils.mem", "su memory");
        reload  = new ReloadCommand("serverutils.reload", "su reload");
    }

    /**
     * Main commands handler.
     *
     * Redirects given command to executor.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean permissionAll = sender.hasPermission("serverutils.all");

        if (args.length == 0) {
            execute(sender, permissionAll);
            return true;
        }

        List<String> executed = new ArrayList<String>();

        // Split every argument into command and execute it
        for (String arg : args) {
            for (String command : arg.split("[\\,,|]")) {

                if (executed.contains(command))
                    continue;
                else
                    executed.add(command);

                if (command.equalsIgnoreCase("tps"))
                    tps.execute(sender, permissionAll);
                else if (command.equalsIgnoreCase("mobs"))
                    mobs.execute(sender, permissionAll);
                else if (command.equalsIgnoreCase("gc"))
                    gc.execute(sender, permissionAll);
                else if (command.equalsIgnoreCase("mem")
                        || command.equalsIgnoreCase("memory"))
                    mem.execute(sender, permissionAll);
                else if (command.equalsIgnoreCase("reload")
                        || command.equalsIgnoreCase("r"))
                    reload.execute(sender, permissionAll);

            }
        }

        return true;
    }

    /**
     * Execute all commands.
     *
     * @param sender Command sender.
     */
    private void execute(CommandSender sender, boolean permissionAll) {
        if (!permissionAll)
            return;

        tps.execute(sender, permissionAll);
        mobs.execute(sender, permissionAll);
        gc.execute(sender, permissionAll);
        mem.execute(sender, permissionAll);
    }
}
