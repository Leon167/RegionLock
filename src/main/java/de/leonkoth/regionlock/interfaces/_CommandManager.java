package de.leonkoth.regionlock.interfaces;

import org.bukkit.command.CommandSender;

/**
 * Created by Leon on 22.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public interface _CommandManager {

    void register(_Command command);
    void unregister(_Command command);
    _Command getCommand(String name);
    boolean executeCommand(String name, String[] args, CommandSender cSender);
    String getName();
    void showUsage(CommandSender cs);

}
