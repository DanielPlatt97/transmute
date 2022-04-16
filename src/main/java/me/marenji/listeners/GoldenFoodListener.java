package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerHealthManager;
import me.marenji.tribute.DiamondTribute;
import me.marenji.tribute.EmeraldTribute;
import me.marenji.tribute.TributeFactory;
import me.marenji.util.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class GoldenFoodListener implements Listener {

    private TransmutePlugin plugin;
    private PlayerHealthManager healthManager;

    public GoldenFoodListener() {
        this.plugin = TransmutePlugin.getInstance();
        this.healthManager = PlayerHealthManager.getInstance();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onConsumeGoldenApple(PlayerItemConsumeEvent event) {
        var itemStack = event.getItem();
        var foodType = itemStack.getType();
        var player = event.getPlayer();
        var tribute = TributeFactory.getTribute(player, foodType, healthManager);
        if (tribute == null) {
            return;
        }

        var tributeSuccess = tribute.applyTribute();
        if (tributeSuccess) {
            if (tribute instanceof DiamondTribute) {
                player.sendMessage(ChatHelper.chat(plugin.getConfig().getString("diamondtribute_success_message")));
            } else if (tribute instanceof EmeraldTribute) {
                player.sendMessage(ChatHelper.chat(plugin.getConfig().getString("emeraldtribute_success_message")));
            }
        } else {
            if (tribute instanceof DiamondTribute) {
                player.sendMessage(ChatHelper.chat(plugin.getConfig().getString("diamondtribute_fail_message")));
            }
        }
    }

}
