package me.marenji.tribute;

import me.marenji.player.PlayerHealthManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class DiamondTribute extends Tribute {

    private PlayerHealthManager healthManager;

    public DiamondTribute( Player player, Block block, PlayerHealthManager healthManager ) {
        super( player, block );
        this.healthManager = healthManager;
    }

    @Override
    public boolean applyTribute() {
        if ( healthManager.applyDefaultHeartGain(tributingPlayer) ) {
            healthManager.setPlayerHealthToMaxHealth(tributingPlayer);
            lightningStrikeTributer();
            destroyTributedBlock();
            return true;
        } else {
            return false;
        }
    }

}
