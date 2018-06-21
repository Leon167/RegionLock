package de.leonkoth.regionlock.manager;

import de.leonkoth.regionlock.RegionLock;
import de.leonkoth.regionlock.Locale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by Leon on 14.03.2018.
 * Project Blockparty2
 * © 2016 - Leon Koth
 */
public class LocaleManager {

    private RegionLock p;
    private FileConfiguration config;

    public LocaleManager(RegionLock p)
    {
        this.p = p;
        config = this.p.getSettingsManager().getConfig();
    }

    public void setUpLocale()
    {
        String localeName = config.getString("config.LocaleFileName");

        FileConfiguration locale;
        File lfile;

        lfile = new File(this.p.getDataFolder(), localeName);
        if (!lfile.exists()) {
            try {
                lfile.createNewFile();
                this.p.getMessageManager().log("Locale file successfully created!");
            } catch (IOException e) {
                Bukkit.getServer().getLogger()
                        .severe(ChatColor.RED + "Could not create " + localeName + "!");
            }
        }

        locale = YamlConfiguration.loadConfiguration(lfile);
        try {
            for(Field field : Locale.class.getDeclaredFields())
            {
                if(field.getType() == String.class)
                {
                    String name = field.getName();
                    String content = (String) field.get(null);
                    if(!locale.contains("Messages." + name))
                        locale.set("Messages." + name, content.replace("§", "&"));
                } else  if(field.getType() == String[].class)
                {
                    String name = field.getName();
                    String[] content = (String[]) field.get(null);
                    for(int i = 0; i<content.length; i++)
                    {
                        content[i] = content[i].replace("§", "&");
                    }
                    if(!locale.contains("Messages." + name))
                        locale.set("Messages." + name, content);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            locale.save(lfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!locale.isConfigurationSection("Messages")) return;
        try {
            for (String name : locale.getConfigurationSection("Messages").getKeys(false)) {
                if(locale.isString("Messages." + name)) {
                    String content = locale.getString("Messages." + name).replace("&", "§");
                    Field field = Locale.class.getDeclaredField(name);
                    field.set(null, content);
                } else if(locale.isList("Messages." + name)) {
                    String[] content = locale.getList("Messages." + name).toArray(new String[0]);
                    for(int i = 0; i<content.length; i++)
                    {
                        content[i] = content[i].replace("&", "§");
                    }
                    Field field = Locale.class.getDeclaredField(name);
                    field.set(null, content);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
