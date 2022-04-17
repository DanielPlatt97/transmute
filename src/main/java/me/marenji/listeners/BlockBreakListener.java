package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.transmutables.TransmutableManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
        var blockBrokenType = event.getBlock().getType();
        handleDiamondBreaking(event);
        var transmutable = TransmutableManager.getInstance().getTransmutable(blockBrokenType);
        if (transmutable == null) return;
        transmutable.transmute(event);
    }

    private void handleDiamondBreaking(BlockBreakEvent event) {
        var blockBrokenType = event.getBlock().getType();
        if (blockBrokenType != Material.DIAMOND_ORE) return;

        var itemHeld = event.getPlayer().getInventory().getItemInMainHand();
        if (itemHeld == null) return;

        var message = ChatColor.BLUE + event.getPlayer().getName() + " mined diamond ore";

        var hasSilkTouch = itemHeld.containsEnchantment(Enchantment.SILK_TOUCH);
        if (hasSilkTouch) {
            message = message + " with Silk Touch";
        }

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
