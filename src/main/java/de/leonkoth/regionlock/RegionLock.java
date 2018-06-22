package de.leonkoth.regionlock;

import de.leonkoth.regionlock.commands.Claim;
import de.leonkoth.regionlock.commands.SetCoordinate;
import de.leonkoth.regionlock.data.Region;
import de.leonkoth.regionlock.interfaces.SerializableStrategy;
import de.leonkoth.regionlock.manager.CommandManager;
import de.leonkoth.regionlock.manager.LocaleManager;
import de.leonkoth.regionlock.manager.MessageManager;
import de.leonkoth.regionlock.manager.SettingsManager;
import de.leonkoth.regionlock.serializing.BinaryStrategy;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
    private HashMap<UUID, Region> creatingRegion;
    private List<Region> regions;

    @Override
    public void onEnable()
    {
        instance = this;

        this.creatingRegion = new HashMap<>();

        this.settingsManager = new SettingsManager(instance);
        this.settingsManager.setup();

        this.messageManager = new MessageManager(instance);

        new LocaleManager(instance).setUpLocale();

        regions = new ArrayList<>();
        SerializableStrategy ss = new BinaryStrategy();
        try {
            ss.openReadableRegion();
            regions = ss.readAllRegion();
            ss.closeReadable();
        } catch (IOException | ClassNotFoundException e) {
            if(!(e instanceof FileNotFoundException))
                e.printStackTrace();
        }

        this.commandManagers = new ArrayList<>();
        CommandManager regionlock = new CommandManager(instance, "regionlock", this.messageManager);
        regionlock.register(new SetCoordinate(instance));
        regionlock.register(new Claim(instance));
        this.commandManagers.add(regionlock);

        for(CommandManager cm : commandManagers)
            this.getCommand(cm.getName()).setExecutor(cm);

    }

    @Override
    public void onDisable()
    {
        if(!regions.isEmpty()) {
            SerializableStrategy ss = new BinaryStrategy();
            try {
                ss.openWriteableRegion();
                for (Region region : regions) {
                    ss.writeRegion(region);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ss.closeWriteable();
            }
        }
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

    public List<Region> getRegions() {
        return regions;
    }

    public HashMap<UUID, Region> getCreatingRegion() {
        return creatingRegion;
    }
}
