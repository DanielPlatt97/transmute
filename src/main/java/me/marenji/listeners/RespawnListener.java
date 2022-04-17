package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerHealthManager;
import me.marenji.player.PlayerMessageManager;
import me.marenji.util.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RespawnListener implements Listener {

    public RespawnListener() {
        Bukkit.getPluginManager().registerEvents(
            this,
            TransmutePlugin.getInstance()
        );
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        var healthManager = PlayerHealthManager.getInstance();
        var plugin = TransmutePlugin.getInstance();

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

        new PlayerMessageManager(player).message("Heart loss penalty cooldown has been applied for 3 minutes");
    }

}
