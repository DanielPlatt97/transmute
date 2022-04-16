package me.marenji.transmutables;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;

public class TreasureTransmutable extends Transmutable{

    // higher quality increases the probability of getting a better treasure
    private int quality;

    public TreasureTransmutable(Material material, int quality) {
        super(material);
        this.quality = quality;
    }

    public void transmute() {
        throw new NotImplementedException();
    }

}
