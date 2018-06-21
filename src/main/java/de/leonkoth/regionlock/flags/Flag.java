package de.leonkoth.regionlock.flags;

import de.leonkoth.regionlock.interfaces._Flag;

/**
 * Created by Leon on 22.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public abstract class Flag implements _Flag {

    private boolean allowed;
    private String name;

    protected Flag(String name, boolean allowed)
    {
        this.name = name;
        this.allowed = allowed;
    }

    @Override
    public String toString()
    {
        String pre = "-"+ this.name + " ";
        if(allowed)
            return pre + "allow";
        return pre + "deny";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMessage() {
        return toString();
    }

    @Override
    public abstract String[] getInfo();

    @Override
    public boolean isAllowed() {
        return allowed;
    }

    @Override
    public void set(boolean allowed) {
        this.allowed = allowed;
    }
}
