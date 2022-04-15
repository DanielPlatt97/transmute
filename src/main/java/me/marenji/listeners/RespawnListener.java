package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.health.PlayerHealthManager;
import me.marenji.util.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RespawnListener implements Listener {

    @SuppressWarnings({"unused"})
    private TransmutePlugin plugin;
    private PlayerHealthManager healthManager;

    public RespawnListener(TransmutePlugin plugin) {
        this.plugin = plugin;
        this.healthManager = new PlayerHealthManager(plugin);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        // apply the status effect 3 ticks after respawn since the player will not be alive yet
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                healthManager.applyPenaltyImmunity(player);
                player.addPotionEffect(new PotionEffect(
                        PotionEffectType.DAMAGE_RESISTANCE, 5 * 20, 4
                ));
            }
        }, 3L);

        player.sendMessage(ChatHelper.chat(
                plugin.getConfig()
                        .getString("maxhealth_penaltyrespawn_message")
        ));
    }

}
