package me.marenji.transmutables;

import me.marenji.health.PlayerHealthManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public abstract class Transmutable {

    private Material material;
    protected PlayerHealthManager playerHealthManager;

    public Transmutable(Material material) {
        this.material = material;
        this.playerHealthManager = PlayerHealthManager.getInstance();
    }

    public Material getMaterial() {
        return material;
    }

    public abstract void transmute(BlockBreakEvent event);

    protected void lightningStrikePlayer(Player player) {
        var location = player.getLocation();
        var world = player.getWorld();
        world.strikeLightningEffect(location);
    }

}
