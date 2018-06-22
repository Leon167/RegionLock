package de.leonkoth.regionlock.data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Leon on 22.06.2018.
 * Project regionlock
 * © 2016 - Leon Koth
 */
public class Region implements Serializable {

    private int xMax = Integer.MIN_VALUE, yMax, zMax;
    private int xMin = Integer.MIN_VALUE, yMin, zMin;

    private UUID owner;
    private List<UUID> member;

    private String name;

    private boolean pvp = true;
    private boolean build = true;
    private boolean canBeOverridden = true;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public int getyMax() {
        return yMax;
    }

    public void setyMax(int yMax) {
        this.yMax = yMax;
    }

    public int getzMax() {
        return zMax;
    }

    public void setzMax(int zMax) {
        this.zMax = zMax;
    }

    public int getxMin() {
        return xMin;
    }

    public void setxMin(int xMin) {
        this.xMin = xMin;
    }

    public int getyMin() {
        return yMin;
    }

    public void setyMin(int yMin) {
        this.yMin = yMin;
    }

    public int getzMin() {
        return zMin;
    }

    public void setzMin(int zMin) {
        this.zMin = zMin;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public List<UUID> getMember() {
        return member;
    }

    public void setMember(List<UUID> member) {
        this.member = member;
    }

    public boolean isPvp() {
        return pvp;
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
    }

    public boolean isBuild() {
        return build;
    }

    public void setBuild(boolean build) {
        this.build = build;
    }

    public boolean isCanBeOverridden() {
        return canBeOverridden;
    }

    public void setCanBeOverridden(boolean canBeOverridden) {
        this.canBeOverridden = canBeOverridden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return xMax == region.xMax &&
                yMax == region.yMax &&
                zMax == region.zMax &&
                xMin == region.xMin &&
                yMin == region.yMin &&
                zMin == region.zMin &&
                pvp == region.pvp &&
                build == region.build &&
                canBeOverridden == region.canBeOverridden &&
                Objects.equals(owner, region.owner) &&
                Objects.equals(member, region.member) &&
                Objects.equals(name, region.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xMax, yMax, zMax, xMin, yMin, zMin, owner, member, name, pvp, build, canBeOverridden);
    }

}
