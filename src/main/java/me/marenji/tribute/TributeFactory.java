package me.marenji.tribute;

import me.marenji.player.PlayerHealthManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public final class TributeFactory {

    public static Tribute getTribute(Player player, Material foodType, PlayerHealthManager healthManager) {
        if (foodType != Material.GOLDEN_APPLE && foodType != Material.GOLDEN_CARROT) {
            return null;
        }

        var blockBelowPlayer = Tribute.getBlockBelowPlayer(player);
        if (
                blockBelowPlayer.getType() == Material.DIAMOND_BLOCK &&
                        foodType == Material.GOLDEN_APPLE
        ) {
            return new DiamondTribute(player, blockBelowPlayer, healthManager);
        } else if ( blockBelowPlayer.getType() == Material.EMERALD_BLOCK ) {
            return new EmeraldTribute(player, blockBelowPlayer);
        } else {
            return null;
        }
    }

}
