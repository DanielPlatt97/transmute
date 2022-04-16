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

    public Transmutable(Material material, String displayName) {
        this.material = material;
        this.displayName = displayName;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDisplayName() {
        return displayName;
    }

    public abstract boolean transmute(BlockBreakEvent event);

    protected boolean canTransmute(BlockBreakEvent event) {
        var player = event.getPlayer();

        var itemHeld = player.getInventory().getItemInMainHand();
        if (itemHeld == null) return false;

        var itemHeldType = itemHeld.getType();
        if (itemHeldType != Material.GOLDEN_PICKAXE) return false;

        return true;
    }

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
