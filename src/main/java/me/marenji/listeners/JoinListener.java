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

    private final TransmutePlugin plugin;
    private final PlayerHealthManager healthManager;
    private final PlayerMessageManager messagePlayer;

    public JoinListener() {
        this.plugin = TransmutePlugin.getInstance();
        this.healthManager = PlayerHealthManager.getInstance();
        this.messagePlayer = PlayerMessageManager.getInstance();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if ( !player.hasPlayedBefore() ) {
            setupNewPlayer(player);
        }

        messagePlayer.sendNextHeartMessage(player);
    }

    private void setupNewPlayer(Player player) {
        healthManager.setMaxHearts(player, plugin.getConfig().getInt("startinghearts"));
        healthManager.setPlayerHealthToMaxHealth(player);
        healthManager.applyPenaltyImmunity(player);
        player.sendMessage(ChatHelper.chat(
                plugin.getConfig()
                        .getString("maxhealth_penaltyjoined_message")
        ));
        messagePlayer.sendNextHeartMessage(player);
    }

}
