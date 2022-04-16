package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerHealthManager;
import me.marenji.player.PlayerMessageManager;
import me.marenji.util.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    public JoinListener() {
        Bukkit.getPluginManager().registerEvents(
            this,
            TransmutePlugin.getInstance()
        );
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if ( !player.hasPlayedBefore() ) {
            setupNewPlayer(player);
        }

        PlayerMessageManager.getInstance().sendNextHeartMessage(player);
    }

    private void setupNewPlayer(Player player) {
        var healthManager = PlayerHealthManager.getInstance();
        var plugin = TransmutePlugin.getInstance();
        healthManager.setMaxHearts(player, plugin.getConfig().getInt("startinghearts"));
        healthManager.setPlayerHealthToMaxHealth(player);
        healthManager.applyPenaltyImmunity(player);
        player.sendMessage(ChatHelper.chat(
                plugin.getConfig()
                        .getString("maxhealth_penaltyjoined_message")
        ));
        PlayerMessageManager.getInstance().sendNextHeartMessage(player);
    }

}
