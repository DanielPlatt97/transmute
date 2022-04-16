package me.marenji.transmutables;

import me.marenji.player.PlayerHealthManager;
import me.marenji.player.PlayerMessageManager;
import me.marenji.util.ChatHelper;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

public class HeartTransmutable extends Transmutable {

    private final int levelRequired;
    private final int heartsGained;

    public HeartTransmutable(Material material, String displayName, int levelRequired, int heartsGained) {
        super(material, displayName);
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

        var playerHealthManager = PlayerHealthManager.getInstance();

        if (playerHealthManager.getMaxHearts(player) != levelRequired) return;

        event.setDropItems(false);

        lightningStrikeBlock(event.getBlock());
        playerHealthManager.addHearts(player, heartsGained);
        PlayerMessageManager.getInstance().sendNextHeartMessage(player);
    }

}
