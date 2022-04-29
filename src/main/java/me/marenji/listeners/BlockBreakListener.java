package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerHealthManager;
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
        handleRareOreLogging(event);

        var player = event.getPlayer();
        var playerHealthManager = PlayerHealthManager.getInstance();
        var playerLevel = playerHealthManager.getMaxHearts(player);
        var transmutable = TransmutableManager.getInstance().getTransmutable(blockBrokenType, playerLevel);
        if (transmutable == null) return;
        transmutable.transmute(event);
    }

    private void handleRareOreLogging(BlockBreakEvent event) {
        var blockBrokenType = event.getBlock().getType();
        ChatColor colour;
        String oreName;
        if (blockBrokenType == Material.DIAMOND_ORE) {
            colour = ChatColor.BLUE;
            oreName = "diamond ore";
        } else if (blockBrokenType == Material.ANCIENT_DEBRIS) {
            colour = ChatColor.DARK_RED;
            oreName = "acient debris";
        } else {
            return;
        }

        var itemHeld = event.getPlayer().getInventory().getItemInMainHand();
        if (itemHeld == null) return;

        var message = colour + event.getPlayer().getName() + " mined " + oreName;

        var hasSilkTouch = itemHeld.containsEnchantment(Enchantment.SILK_TOUCH);
        if (hasSilkTouch) {
            message = message + " with Silk Touch";
        }

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
