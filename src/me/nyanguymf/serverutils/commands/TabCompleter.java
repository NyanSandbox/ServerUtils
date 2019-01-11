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

        List<String> complete = new ArrayList<String>();

        String[] commands = {
                "tps", "mobs",
                "mem", "reload",
                "gc"
        };

        String toComplete = args[args.length - 1];

        for (String command : commands)
            if (command.startsWith(toComplete))
                complete.add(command);

        return complete.size() == 0 ? null : complete;
    }

}
