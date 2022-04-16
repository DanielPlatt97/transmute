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
        Bukkit.getLogger().info("onBreakBlock");
        var player = event.getPlayer();

        var itemHeld = player.getInventory().getItemInMainHand();
        if (itemHeld == null) {
            Bukkit.getLogger().info("no item held");
            return;
        }

        var itemHeldType = itemHeld.getType();
        if (itemHeldType != Material.GOLDEN_PICKAXE) {
            Bukkit.getLogger().info("Not golden pickaxe, is" + itemHeldType.toString());
            player.sendMessage(ChatHelper.chat("Not golden pickaxe, is" + itemHeldType.toString()));
            return;
        };

        var blockBrokenType = event.getBlock().getType();
        var transmutable = transmutableManager.getTransmutable(blockBrokenType);
        if (transmutable == null) {
            Bukkit.getLogger().info("Not transmutable");
            player.sendMessage(ChatHelper.chat("Not transmutable"));
            return;
        };

        transmutable.transmute(event);
    }

}
