package me.marenji.transmutables;

import me.marenji.util.ChatHelper;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

public class HeartTransmutable extends Transmutable {

    private final int levelRequired;
    private final int heartsGained;

    public HeartTransmutable(Material material, int levelRequired, int heartsGained) {
        super(material);
        this.levelRequired = levelRequired;
        this.heartsGained = heartsGained;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public int getHeartsGained() {
        return heartsGained;
    }

    public void transmute(BlockBreakEvent event) {
        var player = event.getPlayer();
        if (playerHealthManager.getMaxHearts(player) != levelRequired) {
            player.sendMessage(ChatHelper.chat("Wrong level"));
            return;
        }

        lightningStrikePlayer(player);
        playerHealthManager.applyDefaultHeartGain(player);
        player.sendMessage(ChatHelper.chat("Level up"));
    }

}
