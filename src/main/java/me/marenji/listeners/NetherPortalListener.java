package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerMessageManager;
import me.marenji.player.PlayerScoreboardManager;
import me.marenji.transmutables.TransmutableManager;
import me.marenji.util.ConfigHelper;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class NetherPortalListener implements Listener {

    public NetherPortalListener() {
        Bukkit.getPluginManager().registerEvents(
            this,
            TransmutePlugin.getInstance()
        );
    }

    @EventHandler
    public void onEnteringPortal(PlayerPortalEvent event) {
        if (event.getCause() != PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) return;
        var player = event.getPlayer();
        var playerScoreboard = new PlayerScoreboardManager(player);
        if (playerScoreboard.getCanEnterNether()) return;

        new PlayerMessageManager(player).message("You must have reached " + ConfigHelper.getNetherLevelRequired() + " hearts to enter the Nether");
        event.setCancelled(true);
    }

}
