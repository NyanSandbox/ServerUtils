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
    private ServerUtils su;

    public MobsCommand(String permission, String command, ServerUtils plugin, MessagesManager mm) {
        super(permission, command, mm);

        su = plugin;
    }

    /**
     * Gets total mobs count on server and
     * send it as message to command sender.
     */
    @Override
    public boolean execute(CommandSender sender, boolean permissionAll, String... args) {
        if (!super.execute(sender, permissionAll)) return false;

        Map<String, Integer> mobsCount = getMobsCount();

        String  monsters = super.mm.getMessage("monsters");
                monsters = StringUtils.replaceVarColored(monsters, String.valueOf(mobsCount.get("monsters")));

        String  animals = super.mm.getMessage("animals");
                animals = StringUtils.replaceVarColored(animals, String.valueOf(mobsCount.get("animals")));

        sender.sendMessage(new String[] {animals, monsters});

        return true;
    }

    /**
     * Gets animals and monsters count around all worlds.
     *
     * @return Map, where key is entity object name (animal/monster)
     * and value is count.
     */
    private Map<String, Integer> getMobsCount() {
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
