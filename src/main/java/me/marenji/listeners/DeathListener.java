package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerHealthManager;
import me.marenji.player.PlayerMessageManager;
import me.marenji.util.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
        Player player = event.getEntity();
        var healthManager = PlayerHealthManager.getInstance();
        var messagePlayer = PlayerMessageManager.getInstance();
        var plugin = TransmutePlugin.getInstance();
        if ( healthManager.isPlayerImmuneToPenalty(player) ) {
            player.sendMessage(ChatHelper.chat(
                    plugin.getConfig()
                            .getString("maxhealth_immunepenalty_message")
            ));
        } else {
            if ( healthManager.applyDefaultHeartLoss(player) ) {
                player.sendMessage(ChatHelper.chat(
                        plugin.getConfig()
                                .getString("maxhealth_healthlost_message")
                ));
            } else {
                player.sendMessage(ChatHelper.chat(
                        plugin.getConfig()
                                .getString("maxhealth_healthminimum_message")
                ));
            }
        }
        messagePlayer.sendNextHeartMessage(player);
    }

}
