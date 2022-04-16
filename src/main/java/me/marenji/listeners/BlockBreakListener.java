package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.transmutables.TransmutableManager;
import me.marenji.util.ChatHelper;
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
        this.transmutableManager = TransmutableManager.getInstance();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        var player = event.getPlayer();

        var itemHeld = player.getInventory().getItemInMainHand();
        if (itemHeld == null) return;

        var itemHeldType = itemHeld.getType();
        if (itemHeldType != Material.GOLDEN_PICKAXE) return;

        var blockBrokenType = event.getBlock().getType();
        var transmutable = transmutableManager.getTransmutable(blockBrokenType);
        if (transmutable == null) return;

        transmutable.transmute(event);
    }

}
