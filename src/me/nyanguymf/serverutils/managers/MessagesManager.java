/**
 * MessagesManager.java 2019-01-09
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.managers;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import me.nyanguymf.serverutils.ServerUtils;
import me.nyanguymf.serverutils.utils.StringUtils;

/**
 * @author nyanguymf
 */
public class MessagesManager {
    private YamlConfiguration   config;

    /**
     * Plugin's main class instance;
     */
    private ServerUtils plugin;

    public MessagesManager(ServerUtils plugin) {
        this.plugin = plugin;

        File    configFile  = new File(plugin.getDataFolder(), "messages.yml");
                config      = YamlConfiguration.loadConfiguration(configFile);

        boolean override = false;

        if (config.isSet("version")) {
            if (!config.getString("version").equals(plugin.getDescription().getVersion()))
                override = true;
        } else {
            override = true;
        }

        plugin.saveResource(configFile.getName(), override); // TODO: true to false

        reload();
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
     * Return configuration section form messages file.
     *
     * @param Path to configuration section in configuration file.
     *
     * @return ConfigurationSection.
     *
     * @since 1.2.
     */
    public ConfigurationSection getSection(String path) {
        return config.getConfigurationSection(path);
    }

    /**
     * Reload messages configuration.
     */
    public void reload() {
        File    configFile  = new File(plugin.getDataFolder(), "messages.yml");
                config      = YamlConfiguration.loadConfiguration(configFile);
    }
}
