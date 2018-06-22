package de.leonkoth.regionlock.interfaces;

import de.leonkoth.regionlock.data.Region;

import java.io.IOException;
import java.util.List;

public interface SerializableStrategy {

    /*
      Setup Serializing the library
     */
    void openWriteableRegion() throws IOException;

    /*
      Setup Deserializing the library
     */
    void openReadableRegion() throws IOException;

    /*
      Write a PlayerInfo
     */
    void writeRegion(Region pi) throws IOException;

    /*
     Read a PlayerInfo
     */
    Region readRegion() throws IOException, ClassNotFoundException;

    /*
     Read all PlayerInfo
     */
    List<Region> readAllRegion() throws IOException, ClassNotFoundException;

    /*
     Finish writing/reading by closing all Streams
     */
    void closeReadable();

    void closeWriteable();

}
