package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerHealthManager;
import me.marenji.player.PlayerMessageManager;
import me.marenji.util.ConfigHelper;
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

        new PlayerMessageManager(player).sendNextHeartMessage();
    }

    private void setupNewPlayer(Player player) {
        var healthManager = PlayerHealthManager.getInstance();
        var plugin = TransmutePlugin.getInstance();
        healthManager.setMaxHearts(player, plugin.getConfig().getInt("startinghearts"));
        healthManager.setPlayerHealthToMaxHealth(player);
        healthManager.applyPenaltyImmunity(player);
        var toPlayer = new PlayerMessageManager(player);
        toPlayer.message(ConfigHelper.getTextPlayerJoined());
    }

}
