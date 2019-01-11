/**
 * MobsCommand.java 2019-01-09
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;

import me.nyanguymf.serverutils.ServerUtils;
import me.nyanguymf.serverutils.managers.MessagesManager;
import me.nyanguymf.serverutils.utils.StringUtils;

/**
 * @author nyanguymf
 *
 */
class MobsCommand extends Command {
    private static ServerUtils su = ServerUtils.getInstance();

    public MobsCommand(String permission, String command) {
        super(permission, command);
    }

    /**
     * Gets total mobs count on server and
     * send it as message to command sender.
     */
    @Override
    public void execute(CommandSender sender, boolean permissionAll) {
        if (!sender.hasPermission(super.permission) && !permissionAll) {
            super.sendNoPermission(sender);
            return;
        }

        Map<String, Integer> mobsCount = getMobsCount();

        String  monsters = MessagesManager.getInstance().getMessage("monsters");
                monsters = StringUtils.replaceVarColored(monsters, String.valueOf(mobsCount.get("monsters")));

        String  animals = MessagesManager.getInstance().getMessage("animals");
                animals = StringUtils.replaceVarColored(animals, String.valueOf(mobsCount.get("animals")));

        sender.sendMessage(new String[] {animals, monsters});
    }

    /**
     * Gets animals and monsters count around all worlds.
     *
     * @return Map, where key is entity object name (animal/monster)
     * and value is count.
     */
    private static Map<String, Integer> getMobsCount() {
        int monstersCount   = 0;
        int animalsCount    = 0;

        List<World> worlds = su.getServer().getWorlds();
        
        for (World world : worlds) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Monster) {
                    monstersCount++;
                } else if (entity instanceof Animals) {
                    animalsCount++;
                }                    
            }
        }

        TreeMap<String, Integer> mobsCount = new TreeMap<String, Integer>();

        mobsCount.put("animals", animalsCount);
        mobsCount.put("monsters", monstersCount);

        return mobsCount;
    }
}
