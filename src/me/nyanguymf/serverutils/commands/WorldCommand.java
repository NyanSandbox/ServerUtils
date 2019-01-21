/**
 * WorldCommand.java 2019-01-21
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
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nyanguymf.serverutils.managers.MessagesManager;
import me.nyanguymf.serverutils.utils.StringUtils;

/**
 * @author nyanguymf
 *
 * @since 1.2
 */
class WorldCommand extends Command {

    public WorldCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    @Override
    public boolean execute(CommandSender sender, boolean permission, String... args) {
        if (!super.execute(sender, permission)) return false;

        if (args.length < 2) {
            if (sender instanceof Player)
                showWorld(sender, ((Player) sender).getWorld());
            else
                showAll(sender);
        } else {
            showWorlds(sender, args, 1);
        }


        return true;
    }

    /**
     * Shows information of specific world.
     *
     * @param sender Command sender to send response.
     *
     * @param world World to get information.
     */
    private void showWorld(CommandSender sender, World world) {
        String format = super.mm.getColoredMessage("world");

        format = StringUtils.replaceVariables(format, new String[] {
                world.getName(),
                String.valueOf(world.getLoadedChunks().length),
                String.valueOf(world.getEntities().size())
        });

        sender.sendMessage(format);
    }

    /**
     * Shows every world from given array.
     *
     * @param sender Command sender to send response.
     *
     * @param worlds World names array.
     *
     * @param offset Offset in worlds array where worlds starts.
     */
    private void showWorlds(CommandSender sender, String[] worlds, int offset) {
        List<String> shown = new ArrayList<String>();

        breakpoint: for (int c = offset; c < worlds.length; c++) {
            String worldNames = worlds[c];

            for (String worldName : worldNames.split("[\\,,|]")) {
                if (worldName.equalsIgnoreCase("all")) {
                    showAll(sender);
                    break breakpoint;
                }

                if (shown.contains(command.toLowerCase()))
                    continue;
                else
                    shown.add(command.toLowerCase());

                World world = Bukkit.getWorld(worldName);

                if (world == null) {
                    String error = super.mm.getColoredMessage("world-not-found");
                    sender.sendMessage(StringUtils.replaceVariable(error, worldName));
                    continue;
                }

                showWorld(sender, world);
            }
        }
    }

    /**
     * Shows information for each world on Server.
     *
     * @param sender Command sender to send response.
     */
    private void showAll(CommandSender sender) {
        for (World world : Bukkit.getWorlds())
            showWorld(sender, world);
    }
}
