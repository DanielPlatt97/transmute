package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.transmutables.TransmutableManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private TransmutePlugin plugin;
    private TransmutableManager transmutableManager;

    public BlockBreakListener() {
        this.plugin = TransmutePlugin.getInstance();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        var player = event.getPlayer();

        var itemHeld = player.getItemInUse();
        if (itemHeld == null) return;

        var itemType = itemHeld.getType();
        if (itemType != Material.GOLDEN_PICKAXE) return;

        var transmutable = transmutableManager.getTransmutable(itemType);
        if (transmutable == null) return;

        transmutable.transmute(event);
    }

}
