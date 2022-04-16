package me.marenji.transmutables;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerHealthManager;
import me.marenji.player.PlayerMessageManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public abstract class Transmutable {

    private Material material;
    private String displayName;
    protected PlayerHealthManager playerHealthManager;
    protected TransmutableManager transmutableManager;
    protected PlayerMessageManager messagePlayer;

    public Transmutable(Material material, String displayName) {
        this.material = material;
        this.displayName = displayName;
        this.playerHealthManager = PlayerHealthManager.getInstance();
        this.transmutableManager = TransmutableManager.getInstance();
        this.messagePlayer = PlayerMessageManager.getInstance();
    }

    public Material getMaterial() {
        return material;
    }

    public String getDisplayName() {
        return displayName;
    }

    public abstract void transmute(BlockBreakEvent event);

    protected void lightningStrikePlayer(Player player) {
        var location = player.getLocation();
        var world = player.getWorld();
        world.strikeLightningEffect(location);
    }

    protected void lightningStrikeBlock(Block block) {
        var location = block.getLocation();
        var world = block.getWorld();
        world.strikeLightningEffect(location);
    }

}
