package me.marenji.transmutables;

import me.marenji.util.ConfigHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.HashMap;

public final class TransmutableManager {

    private static TransmutableManager single_instance = null;

    private HashMap<Material, HeartTransmutable> heartTransmutablesByMaterial;
    private HashMap<Integer, HeartTransmutable> heartTransmutablesByLevel;
    private HashMap<Material, TreasureTransmutable> treasureTransmutablesByMaterial;

    private TransmutableManager()
    {
        var heartTransmutables = ConfigHelper.getHeartTransmutables();
        var treasureTransmutables = ConfigHelper.getTreasureTransmutables();
        initHashMaps(heartTransmutables, treasureTransmutables);
    }

    public static TransmutableManager getInstance()
    {
        if (single_instance == null) {
            single_instance = new TransmutableManager();
        }
        return single_instance;
    }

    private void initHashMaps(Iterable<HeartTransmutable> heartTransmutables, Iterable<TreasureTransmutable> treasureTransmutables) {
        this.heartTransmutablesByMaterial = new HashMap<>();
        this.treasureTransmutablesByMaterial = new HashMap<>();
        this.heartTransmutablesByLevel = new HashMap<>();
        for (var heartTransmutable: heartTransmutables) {
            this.heartTransmutablesByMaterial.put(heartTransmutable.getMaterial(), heartTransmutable);
            this.heartTransmutablesByLevel.put(heartTransmutable.getLevelRequired(), heartTransmutable);
        }
        for (var treasureTransmutable: treasureTransmutables) {
            this.treasureTransmutablesByMaterial.put(treasureTransmutable.getMaterial(), treasureTransmutable);
        }
    }

    public Transmutable getTransmutable(Material material) {
        if (isHeartTransmutable(material)) {
            return getHeartTransmutable(material);
        }

        if (isTreasureTransmutable(material)) {
            return getTreasureTransmutable(material);
        }

        return null;
    }

    public HeartTransmutable getHeartTransmutableByLevel(int level) {
        if (levelHasHeartTransmutable(level)) {
            return getHeartTransmutable(level);
        }

        return null;
    }

    public String getNextHeartTransmutableMessage(int level) {
        var nextHeartTransmutable = getHeartTransmutableByLevel(level);

        if (nextHeartTransmutable == null) {
            return "You are max level. You cannot gain more hearts.";
        }

        return "You must destroy a " + nextHeartTransmutable.getDisplayName() + " with a Golden Pickaxe to gain another heart.";
    }

    private boolean levelHasHeartTransmutable(int heartLevel) {
        return heartTransmutablesByLevel.containsKey(heartLevel);
    }

    private boolean isHeartTransmutable(Material material) {
        return heartTransmutablesByMaterial.containsKey(material);
    }

    private boolean isTreasureTransmutable(Material material) {
        return treasureTransmutablesByMaterial.containsKey(material);
    }

    private HeartTransmutable getHeartTransmutable(Material material) {
        return heartTransmutablesByMaterial.get(material);
    }

    private HeartTransmutable getHeartTransmutable(int heartLevel) {
        return heartTransmutablesByLevel.get(heartLevel);
    }

    private TreasureTransmutable getTreasureTransmutable(Material material) {
        return treasureTransmutablesByMaterial.get(material);
    }

}
