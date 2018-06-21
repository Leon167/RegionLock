package de.leonkoth.regionlock.flags;

/**
 * Created by Leon on 22.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public class PvP extends Flag {


    public PvP(boolean allowed) {
        super("PvP", allowed);
    }

    @Override
    public String[] getInfo() {
        return new String[]{"Set the PvP flag to allow or deny PvP in the selected region."};
    }
}
