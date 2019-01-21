/**
 * PlayerCommand.java 2019-01-20
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nyanguymf.serverutils.commands.Command;
import me.nyanguymf.serverutils.managers.MessagesManager;
import me.nyanguymf.serverutils.utils.StringUtils;

/**
 * @author nyanguymf
 *
 * @since 1.2
 */
public class PlayerCommand extends Command {
    private HpCommand hp;
    private OpCommand op;
    private UuidCommand uuid;
    private IpCommand ip;
    private ExpCommand exp;
    private LevelCommand level;
    private FoodCommand food;
    private ExhaustionCommand exhaustion;
    private GamemodeCommand gamemode;
    private FlyCommand fly;
    private WorldCommand world;
    private CoordinatesCommand coord;

    public PlayerCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);

        hp          = new HpCommand("serverutils.player.hp", command + " hp", mm);
        op          = new OpCommand("serverutils.player.op", command + " op", mm);
        uuid        = new UuidCommand("serverutils.player.uuid", command + "uuid", mm);
        ip          = new IpCommand("serverutils.player.ip", command + " ip", mm);
        exp         = new ExpCommand("serverutils.player.exp", command + " exp", mm);
        level       = new LevelCommand("serverutils.player.level", command + " level", mm);
        food        = new FoodCommand("serverutils.player.food", command + " food", mm);
        exhaustion  = new ExhaustionCommand("serverutils.player.exhaustion", command + " exhaustion", mm);
        gamemode    = new GamemodeCommand("serverutils.player.gamemode", command + " gamemode", mm);
        fly         = new FlyCommand("serverutils.player.fly", command + " fly", mm);
        world       = new WorldCommand("serverutils.player.world", command + " world", mm);
        coord       = new CoordinatesCommand("serverutils.player.coordinates", command + " coordinates", mm);
    }

    @Override
    public boolean execute(CommandSender sender, boolean permission, String... args) {
        if (!super.execute(sender, permission)) return false;

        if (args.length < 2) {
            String error = super.mm.getColoredMessage("usage.player");
            sender.sendMessage(error);
            return false;
        }

        String playerName   = args[1];
        Player player       = null;
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.getName().equalsIgnoreCase(playerName)) {
                player = onlinePlayer;
                break;
            }
        }

        if (player == null) {
            String error = super.mm.getColoredMessage("player-not-found");
            sender.sendMessage(StringUtils.replaceVariable(error, playerName));
            return false;
        }

        if (args.length == 2) {
            executeAll(sender, player);
            return true;
        } else {
            route(sender, player, args, 2);
            return true;
        }
    }

    /**
     * Routes all given commands to specific executors.
     *
     * @param sender CommandSender to send response.
     *
     * @param player Player to get info.
     *
     * @param args Commands array.
     *
     * @param offset Offset that identifies commands start in args array.
     */
    private void route(CommandSender sender, Player player, String[] args, int offset) {
        Set<String> labels = super.mm.getSection("player.stats").getKeys(false);

        String header = super.mm.getColoredMessage("player.stats.header");
        sender.sendMessage(StringUtils.replaceVariable(header, player.getDisplayName()));

        boolean permissionAll = sender.hasPermission("servertils.player.all");

        List<String> executed = new ArrayList<String>();

        // Split every argument into command and execute it
        for (int c = offset; c < args.length; c++) {
            String arg = args[c];

            for (String command : arg.split("[\\,,|]")) {

                if (executed.contains(command.toLowerCase()))
                    continue;
                else
                    executed.add(command.toLowerCase());

                if (!labels.contains(command.toLowerCase()))
                    continue;
                else
                    execute(command.toLowerCase(), sender, permissionAll, player);
            }
        }
    }

    /**
     * Executes all the commands.
     *
     * @param sender CommandSender to send response.
     *
     * @param player Player to get info.
     */
    private void executeAll(CommandSender sender, Player player) {
        Set<String> labels = super.mm.getSection("player.stats").getKeys(false);

        route(sender, player, labels.toArray(new String[labels.size()]), 0);
    }

    /**
     * Executes specific command.
     *
     * @param command
     */
    private void execute(String command, CommandSender sender, boolean permission, Player player) {
        switch (command) {
        case "hp":
            hp.execute(sender, permission, player);
            break;
        case "op":
            op.execute(sender, permission, player);
            break;
        case "uuid":
            uuid.execute(sender, permission, player);
            break;
        case "ip":
            ip.execute(sender, permission, player);
            break;
        case "exp":
            exp.execute(sender, permission, player);
            break;
        case "level":
            level.execute(sender, permission, player);
            break;
        case "food":
            food.execute(sender, permission, player);
            break;
        case "exhaustion":
            exhaustion.execute(sender, permission, player);
            break;
        case "gamemode":
            gamemode.execute(sender, permission, player);
            break;
        case "fly":
            fly.execute(sender, permission, player);
            break;
        case "world":
            world.execute(sender, permission, player);
            break;
        case "coord":
            coord.execute(sender, permission, player);
            break;

        default:
            break;
        }
    }
}
