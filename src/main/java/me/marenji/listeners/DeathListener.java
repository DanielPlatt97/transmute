package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerHealthManager;
import me.marenji.player.PlayerMessageManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    public DeathListener() {
        Bukkit.getPluginManager().registerEvents(
            this,
            TransmutePlugin.getInstance()
        );
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        var player = event.getEntity();
        var healthManager = PlayerHealthManager.getInstance();
        var plugin = TransmutePlugin.getInstance();
        var toPlayer = new PlayerMessageManager(player);

        if ( healthManager.isPlayerImmuneToPenalty(player) ) {
            toPlayer.message("You have died. Your max health penalty is on cooldown, so you have not lost a heart");
        } else {
            if ( healthManager.applyDefaultHeartLoss(player) ) {
                toPlayer.message("You have died. You have returned, but you have one less heart");
            } else {
                toPlayer.message("You have died. You have no hearts left, so you have not lost a heart");
            }
        }
        new PlayerMessageManager(player).sendNextHeartMessage();
    }

}
