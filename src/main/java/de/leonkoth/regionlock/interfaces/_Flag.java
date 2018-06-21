package de.leonkoth.regionlock.interfaces;

/**
 * Created by Leon on 22.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public interface _Flag {

    String getName();
    String getMessage();
    String[] getInfo();
    boolean isAllowed();
    void set(boolean allowed);

}
