package de.leonkoth.regionlock.commands;

import de.leonkoth.regionlock.interfaces._Command;
import org.bukkit.command.CommandSender;

/**
 * Created by Leon on 22.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public class SetCoordinate implements _Command {

    private String name = "setCoordinate";
    private String[] aliases = new String[] {"setCoord", "setPos"};


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getAliases() {
        return aliases;
    }

    @Override
    public boolean accept(String[] args) {
        return false;
    }

    @Override
    public void handleFalseUsage(String[] args, CommandSender cSender) {

    }

    @Override
    public void handle(String[] args, CommandSender cSender) {

    }
}
