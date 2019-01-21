/**
 * TabCompleter.java 2019-01-09
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author nyanguymf
 *
 */
public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0)
            return null;

        // Complete command
        if (args.length == 1) {
            return complete(args, new String[] {
                    "tps", "mobs",
                    "mem", "reload",
                    "gc", "world",
                    "uptime", "player"
            });
        }

        if ((args.length == 2) && (args[0].equalsIgnoreCase("player"))) {
            return null; // return standart - Players.
        }

        if ((args.length == 2) && (args[0].equalsIgnoreCase("world"))) {
            List<World> worldsList  = Bukkit.getWorlds();
            String[]    worlds      = new String[worldsList.size()];

            for (int c = 0; c < worlds.length; c++)
                worlds[c] = worldsList.get(c).getName();
            
            return complete(args, worlds);
        }

        if ((args.length == 3) && (args[0].equalsIgnoreCase("player"))) {
            return complete(args, new String[] {
                    "op", "uuid", "hp", "ip",
                    "exp", "level", "food",
                    "exhaustion", "gamemode",
                    "fly", "world", "coord"
            });
        }

        return null;
    }

    private List<String> complete(String[] args, String[] completers){
        List<String> complete = new ArrayList<String>();

        String toComplete = args[args.length - 1];

        for (String command : completers)
            if (command.startsWith(toComplete))
                complete.add(command);

        return complete.size() == 0 ? null : complete;
    }
}
