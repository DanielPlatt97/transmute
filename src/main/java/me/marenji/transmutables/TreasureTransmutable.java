package me.marenji.transmutables;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

public class TreasureTransmutable extends Transmutable{



    public TreasureTransmutable(Material material, String displayName) {
        super(material, displayName);
    }

    public boolean transmute(BlockBreakEvent event) {
        if (!canTransmute(event)) return false;

        var treasureManager = TreasureManager.getInstance();
        var droppedItem = treasureManager.getRandomItemStackForMaterial(this.material);

        var block = event.getBlock();
        var location = block.getLocation();
        var world = block.getWorld();
        world.dropItem(location, droppedItem);
        return true;
    }

}
