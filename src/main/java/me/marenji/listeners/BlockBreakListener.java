package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.health.PlayerHealthManager;
import me.marenji.util.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BlockBreakListener implements Listener {

    private TransmutePlugin plugin;
    private PlayerHealthManager healthManager;

    public BlockBreakListener(TransmutePlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        var player = event.getPlayer();

        var itemHeld = player.getItemInUse();
        if (itemHeld == null) return;

        var itemType = itemHeld.getType();
        if (itemType != Material.GOLDEN_PICKAXE) return;


    }

}
