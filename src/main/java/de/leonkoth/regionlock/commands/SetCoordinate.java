package de.leonkoth.regionlock.commands;

import de.leonkoth.regionlock.Locale;
import de.leonkoth.regionlock.RegionLock;
import de.leonkoth.regionlock.data.Region;
import de.leonkoth.regionlock.interfaces._Command;
import de.leonkoth.regionlock.manager.MessageManager;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Leon on 22.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public class SetCoordinate implements _Command {

    private String name = "setCoordinate";
    private String[] aliases = new String[] {"setCoord", "setPos"};

    private RegionLock regionLock;

    public SetCoordinate(RegionLock regionLock)
    {
        this.regionLock = regionLock;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getAliases() {
        return aliases;
    }

    @Override
    public boolean accept(String[] args, CommandSender cSender) {
        return cSender instanceof Player && args.length == 1 && ("x".equalsIgnoreCase(args[0]) || "y".equalsIgnoreCase(args[0]));
    }

    @Override
    public void handleFalseUsage(String[] args, CommandSender cSender, MessageManager messageManager) {
        if(cSender instanceof Player) {
            if (args.length < 1) {
                messageManager.msg(cSender, MessageManager.MessageType.ERROR, Locale.TOO_FEW_ARGS);
            } else if (args.length == 1) {
                messageManager.msg(cSender, MessageManager.MessageType.ERROR, Locale.WRONG_ARG.replace("%ARG_W%", args[0]).replace("%ARG_R%", "x or y"));
            } else
                messageManager.msg(cSender, MessageManager.MessageType.ERROR, Locale.TOO_MANY_ARGS);
            messageManager.msg(cSender, MessageManager.MessageType.INFO, Locale.COMMAND_SETCOORDINATE_USAGE);
        } else
            messageManager.msg(cSender, MessageManager.MessageType.ERROR, Locale.CS_HAS_TO_BE_A_PLAYER);
    }

    @Override
    public void handle(String[] args, CommandSender cSender, MessageManager messageManager) {
        Player p = (Player)cSender;
        UUID uuid = p.getUniqueId();
        if(regionLock.getCreatingRegion().containsKey(uuid))
        {
            regionLock.getCreatingRegion().put(uuid, this.setPos(p.getLocation(), regionLock.getCreatingRegion().get(uuid), args[0]));
        } else
        {
            regionLock.getCreatingRegion().put(uuid, this.setPos(p.getLocation(), new Region(), args[0]));
        }
        messageManager.msg(cSender, MessageManager.MessageType.INFO, Locale.POINT_SET);
    }

    private Region setPos(Location loc, Region rg, String pos){
        if("x".equalsIgnoreCase(pos))
        {
            rg.setxMin(loc.getBlockX());
            rg.setyMin(loc.getBlockY());
            rg.setzMin(loc.getBlockZ());
        } else
        {
            rg.setxMax(loc.getBlockX());
            rg.setyMax(loc.getBlockY());
            rg.setzMax(loc.getBlockZ());
        }
        return rg;
    }
}
