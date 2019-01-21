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
import me.nyanguymf.serverutils.commands.player.PlayerCommand;
import me.nyanguymf.serverutils.commands.tps.TPSCommand;
import me.nyanguymf.serverutils.managers.MessagesManager;

/**
 * @author nyanguymf
 *
 */
public class SUCommand implements CommandExecutor {
    private MobsCommand     mobs;
    private GCCommand       gc;
    private WorldCommand    world;
    private MemoryCommand   mem;
    private ReloadCommand   reload;
    private TPSCommand      tps;
    private UptimeCommand   uptime;
    private PlayerCommand   player;

    public SUCommand(ServerUtils plugin, MessagesManager mm) {
        mobs    = new MobsCommand("serverutils.mobs", "su mobs", plugin, mm);
        gc      = new GCCommand("serverutils.gc", "su gc", mm);
        world   = new WorldCommand("serverutils.world", "su world", mm);
        tps     = new TPSCommand("serverutils.tps", "su tps", plugin, mm);
        mem     = new MemoryCommand("serverutils.mem", "su memory", mm);
        reload  = new ReloadCommand("serverutils.reload", "su reload", mm);
        uptime  = new UptimeCommand("serverutils.uptime", "su uptime", mm);
        player  = new PlayerCommand("serverutils.player", "su player", mm);
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

        /* since 1.2 */
        if (args[0].equalsIgnoreCase("player")) {
            player.execute(sender, permissionAll, args);
            return true;
        }

        List<String> executed = new ArrayList<String>();

        // Split every argument into command and execute it
        for (String arg : args) {
            for (String command : arg.split("[\\,,|]")) {

                if (executed.contains(command.toLowerCase()))
                    continue;
                else
                    executed.add(command.toLowerCase());

                if (command.equalsIgnoreCase("tps"))
                    tps.execute(sender, permissionAll);
                else if (command.equalsIgnoreCase("mobs"))
                    mobs.execute(sender, permissionAll);
                else if (command.equalsIgnoreCase("gc"))
                    gc.execute(sender, permissionAll);
                else if (command.equalsIgnoreCase("world"))     /* since 1.2 */
                    world.execute(sender, permissionAll, args);
                else if (command.equalsIgnoreCase("uptime"))
                    uptime.execute(sender, permissionAll);
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
        uptime.execute(sender, permissionAll);
    }
}
