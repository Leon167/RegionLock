package de.leonkoth.regionlock.serializing;

import de.leonkoth.regionlock.data.Region;
import de.leonkoth.regionlock.interfaces.SerializableStrategy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leon on 19.03.2018.
 * Project Blockparty2
 * © 2016 - Leon Koth
 */
public class BinaryStrategy implements SerializableStrategy {

    private String path;

    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public BinaryStrategy() {
        this.path = "plugins//RegionLock//regions.data";
        File file = new File(this.path);
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger()
                        .severe(ChatColor.RED + "Could not create regions.data!");
            }
        }
    }

    @Override
    public void openWriteableRegion() throws IOException {
        fos = new FileOutputStream(path);
        oos = new ObjectOutputStream(fos);
    }

    @Override
    public void openReadableRegion() throws IOException {
        fis = new FileInputStream(path);
        ois = new ObjectInputStream(fis);
    }

    @Override
    public void writeRegion(Region pi) throws IOException {
        oos.writeObject(pi);
        oos.flush();
    }

    @Override
    public Region readRegion() throws IOException, ClassNotFoundException {
        try{
            return (Region) ois.readObject();
        }
        catch(EOFException e){
            return null;
        }
    }

    @Override
    public List<Region> readAllRegion() throws IOException, ClassNotFoundException {
        ArrayList<Region> playerInfos = new ArrayList<>();
        Region r;
        while((r = this.readRegion()) != null)
        {
            playerInfos.add(r);
        }
        return playerInfos;
    }

    @Override
    public void closeReadable() {
        try{
            ois.close();
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void closeWriteable() {
        try{
            oos.close();
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
