package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.health.PlayerHealthManager;
import me.marenji.util.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private TransmutePlugin plugin;
    private PlayerHealthManager healthManager;

    public JoinListener() {
        this.plugin = TransmutePlugin.getInstance();
        this.healthManager = PlayerHealthManager.getInstance();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if ( !player.hasPlayedBefore() ) {
            healthManager.setMaxHearts(player, plugin.getConfig().getInt("startinghearts"));
            healthManager.setPlayerHealthToMaxHealth(player);
            healthManager.applyPenaltyImmunity(player);
            player.sendMessage(ChatHelper.chat(
                    plugin.getConfig()
                            .getString("maxhealth_penaltyjoined_message")
            ));
        }
    }

}
