package de.leonkoth.regionlock.manager;


import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.io.IOException;

public class SettingsManager {

	private Plugin p;
	private FileConfiguration config;
	private File cfile;

	public SettingsManager(Plugin p)
	{
		this.p = p;
	}

    /**
     * To setup the config file
     *
     */
	public void setup() {
		this.cfile = new File(p.getDataFolder(), "config.yml");
		this.config = p.getConfig();

		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}

        config.options().copyDefaults(true);
        try {
            config.save(cfile);
        } catch (IOException e) {
            e.printStackTrace();
			this.p.getLogger()
					.severe(ChatColor.RED + "Could not create data.yml!");
        }

	}


    /**
     * To get the config file
     * @return the config file, Used in:
     *
     */
	public FileConfiguration getConfig() {
		return this.config;
	}

    /**
     * To save the current config file
     */
	public void saveConfig() {
		try {
			this.config.save(this.cfile);
			//MessageManager.getInstance().log("Config successfully saved!");
		} catch (IOException e) {
			this.p.getLogger()
					.severe(ChatColor.RED + "Could not save config.yml!");
		}
	}

    /**
     * To reload the config file
     */
	public void reloadConfig() {
		this.config = YamlConfiguration.loadConfiguration(this.cfile);
	}

    /**
     * To get the PluginDescriptionFile
     * @return the file
     */
	public PluginDescriptionFile getDesc() {
		return this.p.getDescription();
	}
}