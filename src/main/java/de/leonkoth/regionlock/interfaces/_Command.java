package de.leonkoth.regionlock.interfaces;

import de.leonkoth.regionlock.manager.MessageManager;
import org.bukkit.command.CommandSender;

/**
 * Created by Leon on 20.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public interface _Command {

    String getName();
    String[] getAliases();
    boolean accept(String[] args, CommandSender cSender);
    void handleFalseUsage(String[] args, CommandSender cSender, MessageManager messageManager);
    void handle(String[] args, CommandSender cSender, MessageManager messageManager);

}
