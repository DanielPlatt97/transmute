package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.transmutables.TransmutableManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    public BlockBreakListener() {
        Bukkit.getPluginManager().registerEvents(
            this,
            TransmutePlugin.getInstance()
        );
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        var player = event.getPlayer();

        var itemHeld = player.getInventory().getItemInMainHand();
        if (itemHeld == null) return;

        var itemHeldType = itemHeld.getType();
        if (itemHeldType != Material.GOLDEN_PICKAXE) return;

        var blockBrokenType = event.getBlock().getType();
        var transmutable = TransmutableManager.getInstance().getTransmutable(blockBrokenType);
        if (transmutable == null) return;

        transmutable.transmute(event);
    }

}
