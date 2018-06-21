package de.leonkoth.regionlock;

import de.leonkoth.regionlock.commands.SetCoordinate;
import de.leonkoth.regionlock.manager.CommandManager;
import de.leonkoth.regionlock.manager.LocaleManager;
import de.leonkoth.regionlock.manager.MessageManager;
import de.leonkoth.regionlock.manager.SettingsManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leon on 20.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public class RegionLock extends JavaPlugin {

    private RegionLock instance;
    private SettingsManager settingsManager;
    private MessageManager messageManager;
    private List<CommandManager> commandManagers;

    public void onEnable()
    {
        instance = this;

        this.settingsManager = new SettingsManager(instance);
        this.settingsManager.setup();

        new LocaleManager(instance).setUpLocale();

        this.messageManager = new MessageManager(instance);

        this.commandManagers = new ArrayList<>();
        CommandManager regionlock = new CommandManager(instance, "regionlock");
        regionlock.register(new SetCoordinate());
        this.commandManagers.add(regionlock);

        for(CommandManager cm : commandManagers)
            this.getCommand(cm.getName()).setExecutor(new CommandManager(instance, cm.getName()));

    }

    public RegionLock getInstance() {
        return instance;
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public List<CommandManager> getCommandManagers() {
        return commandManagers;
    }
}
