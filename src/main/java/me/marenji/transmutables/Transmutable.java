package me.marenji.transmutables;

import org.bukkit.Material;

public abstract class Transmutable {

    private Material material;

    public Transmutable(Material material) {
        this.material = material;
    }

    public abstract void transmute();

}
