package de.leonkoth.regionlock.manager;

import de.leonkoth.regionlock.RegionLock;
import de.leonkoth.regionlock.interfaces._Command;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Leon on 20.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public class CommandManager implements CommandExecutor{

    private List<_Command> commands;
    private String name;
    private RegionLock rl;

    public CommandManager(RegionLock rl, String name)
    {
        this.rl = rl;
        this.name = name;
        commands = new ArrayList<_Command>();
    }

    public CommandManager(RegionLock rl, String name, List<_Command> commands)
    {
        this(rl, name);
        if(commands != null)
        {
            this.commands = commands;
        }
    }

    public void register(_Command command)
    {
        if(!commands.contains(command))
            this.commands.add(command);
    }

    public void unregister(_Command command)
    {
        commands.remove(command);
    }

    public _Command getCommand(String name)
    {
        for(_Command cmd : commands)
        {
            if(cmd.getName().equalsIgnoreCase(name))
                return cmd;
        }
        return null;
    }

    public boolean executeCommand(String name, String[] args, CommandSender cSender)
    {
        for(_Command cmd : commands)
        {
            if(cmd.getName().equalsIgnoreCase(name))
            {
                if(cmd.accept(args))
                {
                    cmd.handle(args, cSender);
                }
                else
                {
                    cmd.handleFalseUsage(args, cSender);
                }
                return true;
            }
            else
            {
                for(String alias : cmd.getAliases())
                {
                    if(name.equalsIgnoreCase(alias))
                    {
                        if(cmd.accept(args))
                        {
                            cmd.handle(args, cSender);
                        }
                        else
                        {
                            cmd.handleFalseUsage(args, cSender);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getName()
    {
        return this.name;
    }

    private void showUsage(CommandSender cs)
    {
        cs.sendMessage(new String[]{""});
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length > 0)
        {
            if(this.executeCommand(args[0], Arrays.copyOfRange(args, 1, args.length), commandSender))
            {
                return true;
            }
        }
        this.showUsage(commandSender);
        return false;
    }
}
