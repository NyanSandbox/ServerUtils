/**
 * ServerUtils.java 2019-01-09
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils;

import org.bukkit.plugin.java.JavaPlugin;

import me.nyanguymf.serverutils.commands.SUCommand;
import me.nyanguymf.serverutils.commands.TPSCommand;
import me.nyanguymf.serverutils.commands.TabCompleter;

/**
 * @author nyanguymf
 */
public class ServerUtils extends JavaPlugin {
    private static ServerUtils instance;

    private static TPSCommand TPSCommand;

    public void onEnable() {
        instance    = this;
        TPSCommand  = new TPSCommand("serverutils.tps", "su tps");

        getCommand("serverutils").setExecutor(new SUCommand());
        getCommand("serverutils").setTabCompleter(new TabCompleter());

        getLogger().info("Plugin enabled.");
    }

    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);

        getLogger().info("Plugin disabled.");
    }

    /**
     * Gets main plugin's class object.
     *
     * @return Plugin instance.
     */
    public static ServerUtils getInstance() {
        return instance;
    }

    /**
     * Gets TPSCommand object.
     *
     * @return TPSCommand object
     */
    public static TPSCommand getTPSCommand() {
        return TPSCommand;
    }
}
