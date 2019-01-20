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
import me.nyanguymf.serverutils.commands.TabCompleter;
import me.nyanguymf.serverutils.managers.MessagesManager;

/**
 * @author nyanguymf
 */
public class ServerUtils extends JavaPlugin {
    private ServerUtils     instance;
    private MessagesManager mm;

    public void onEnable() {
        instance = this;

        getCommand("serverutils").setExecutor(new SUCommand(instance, mm));
        getCommand("serverutils").setTabCompleter(new TabCompleter());

        getLogger().info("Plugin enabled.");
    }

    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);

        getLogger().info("Plugin disabled.");
    }
}
