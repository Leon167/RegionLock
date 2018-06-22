package de.leonkoth.regionlock.serializing;

import de.leonkoth.regionlock.data.Region;
import de.leonkoth.regionlock.interfaces.SerializableStrategy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLStrategy implements SerializableStrategy {

    private String path;
    private FileInputStream fis;
    private FileOutputStream fos;
    private XMLEncoder xmle;
    private XMLDecoder xmlde;

    public XMLStrategy(){
        this.path = "plugins//RegionLock//data.xml";
        File file = new File(this.path);
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger()
                        .severe(ChatColor.RED + "Could not create data.xml!");
            }
        }
    }

    @Override
    public void openWriteableRegion() throws IOException {
        fos = new FileOutputStream(this.path);
        xmle = new XMLEncoder(fos);
    }

    @Override
    public void openReadableRegion() throws IOException {
        fis = new FileInputStream(this.path);
        xmlde = new XMLDecoder(fis);
    }

    @Override
    public void writeRegion(Region pi) throws IOException {
        xmle.writeObject(pi);
    }

    @Override
    public Region readRegion() throws IOException, ClassNotFoundException {
        try{
            return (Region) xmlde.readObject();
        }catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    @Override
    public List<Region> readAllRegion() throws IOException, ClassNotFoundException {
        ArrayList<Region> playerInfos = new ArrayList<>();
        Region pi;
        while ((pi = readRegion()) != null)
            playerInfos.add(pi);
        return playerInfos;
    }

    @Override
    public void closeReadable() {
        try{
            fis.close();
            xmlde.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void closeWriteable() {
        try{
            fos.flush();
            xmle.close();
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
