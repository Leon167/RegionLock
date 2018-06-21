package de.leonkoth.regionlock.interfaces;

import org.bukkit.command.CommandSender;

/**
 * Created by Leon on 20.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public interface _Command {

    String getName();
    String[] getAliases();
    boolean accept(String[] args);
    void handleFalseUsage(String[] args, CommandSender cSender);
    void handle(String[] args, CommandSender cSender);

}
