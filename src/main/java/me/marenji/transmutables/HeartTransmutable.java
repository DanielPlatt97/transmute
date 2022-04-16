package me.marenji.transmutables;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;

public class HeartTransmutable extends Transmutable {

    private int levelRequired;
    private int heartsGained;

    public HeartTransmutable(Material material, int levelRequired, int heartsGained) {
        super(material);
        this.levelRequired = levelRequired;
        this.heartsGained = heartsGained;
    }

    public void transmute() {
        throw new NotImplementedException();
    }

}
