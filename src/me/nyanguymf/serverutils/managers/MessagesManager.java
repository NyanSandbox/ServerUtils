/**
 * MessagesManager.java 2019-01-09
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.managers;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import me.nyanguymf.serverutils.ServerUtils;
import me.nyanguymf.serverutils.utils.StringUtils;

/**
 * @author nyanguymf
 *
 * Singleton pattern.
 */
public class MessagesManager {
    private YamlConfiguration   config;
    
    private static MessagesManager instance;

    /**
     * Plugin's main class instance;
     */
    private ServerUtils plugin;

    private MessagesManager() {
        plugin   = ServerUtils.getInstance();
        instance = this;

        File    configFile  = new File(plugin.getDataFolder(), "messages.yml");
                config      = YamlConfiguration.loadConfiguration(configFile);

        plugin.saveResource(configFile.getName(), true); // true to false
    }

    /**
     * Typical singleton fabric method.
     *
     * @return instance.
     */
    public static MessagesManager getInstance() {
        if (instance != null)
            return instance;
        else
            return new MessagesManager();
    }

    /**
     * Gets message from configuration file.
     *
     * @param path Path to message in configuration file
     *
     * @return Message from configuration file.
     */
    public String getMessage(String path) {
        return config.getString(path).replace("\\n", "\n");
    }

    /**
     * Same as {@link #getMessage(String)}, but with all
     * Bukkit colors replaced.
     */
    public String getColoredMessage(String path) {
        return StringUtils.replaceColors(getMessage(path));
    }

    /**
     * Reload messages configuration.
     */
    public void reload() {
        File    configFile  = new File(plugin.getDataFolder(), "messages.yml");
                config      = YamlConfiguration.loadConfiguration(configFile);
    }
}
