package me.marenji.transmutables;

import org.bukkit.Material;

public final class TransmutableManager {

    private static TransmutableManager single_instance = null;

    private TransmutableManager()
    {

    }

    public static TransmutableManager getInstance()
    {
        if (single_instance == null) {
            throw new AssertionError("The singleton has not been initialised, call init first");
        }
        return single_instance;
    }

    public synchronized static TransmutableManager init(T x) {
        if (single_instance != null)
        {
            throw new AssertionError("The singleton was already initialised");
        }
        single_instance = new TransmutableManager(x);
        return single_instance;
    }

    private HeartTransmutable[] heartTransmutables = {
        new HeartTransmutable(Material material, int levelRequired, int heartsGained)
    };

    private TreasureTransmutable[] treasureTransmutables = {
            new TreasureTransmutable()
    };

}
