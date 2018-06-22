package de.leonkoth.regionlock.commands;

import de.leonkoth.regionlock.Locale;
import de.leonkoth.regionlock.RegionLock;
import de.leonkoth.regionlock.data.Region;
import de.leonkoth.regionlock.interfaces._Command;
import de.leonkoth.regionlock.manager.MessageManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Leon on 22.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public class Claim implements _Command {

    private String name = "claim";

    private RegionLock regionLock;

    public Claim(RegionLock regionLock)
    {
        this.regionLock = regionLock;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public boolean accept(String[] args, CommandSender cSender) {
        return cSender instanceof Player && args.length == 1;
    }

    @Override
    public void handleFalseUsage(String[] args, CommandSender cSender, MessageManager messageManager) {
        if(cSender instanceof Player) {
            if (args.length < 1) {
                messageManager.msg(cSender, MessageManager.MessageType.ERROR, Locale.TOO_FEW_ARGS);
            } else if (args.length > 1)
                messageManager.msg(cSender, MessageManager.MessageType.ERROR, Locale.TOO_MANY_ARGS);
            messageManager.msg(cSender, MessageManager.MessageType.INFO, Locale.COMMAND_CLAIM_USAGE);
        } else
            messageManager.msg(cSender, MessageManager.MessageType.ERROR, Locale.CS_HAS_TO_BE_A_PLAYER);
    }

    @Override
    public void handle(String[] args, CommandSender cSender, MessageManager messageManager) {
        Player p = (Player)cSender;
        UUID uuid = p.getUniqueId();
        if(regionLock.getCreatingRegion().containsKey(uuid)) {
            Region r = regionLock.getCreatingRegion().get(uuid);
            if(r != null && r.getxMax() != Integer.MIN_VALUE && r.getxMin() != Integer.MIN_VALUE) {
                for (Region rg : regionLock.getRegions()) {
                    if(args[0].equalsIgnoreCase(rg.getName()))
                    {
                        messageManager.msg(cSender, MessageManager.MessageType.ERROR, Locale.REGION_ALREADY_EXISTS);
                        return;
                    }
                }
                r.setName(args[0]);
                regionLock.getRegions().add(r);
                regionLock.getCreatingRegion().remove(uuid);
                messageManager.msg(cSender, MessageManager.MessageType.INFO, Locale.REGION_SET.replace("%NAME%", args[0]));
                return;
            }
        }
        messageManager.msg(cSender, MessageManager.MessageType.ERROR, Locale.NOT_SET_REGION_PONTS);
    }
}
