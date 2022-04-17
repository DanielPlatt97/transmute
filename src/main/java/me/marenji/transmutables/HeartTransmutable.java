package me.marenji.transmutables;

import me.marenji.player.PlayerHealthManager;
import me.marenji.player.PlayerMessageManager;
import me.marenji.util.ChatHelper;
import me.marenji.util.ConfigHelper;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

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

    @Override
    protected  boolean canTransmute(BlockBreakEvent event) {
        if (!super.canTransmute(event)) return false;
        var player = event.getPlayer();
        var playerHealthManager = PlayerHealthManager.getInstance();
        if (playerHealthManager.getMaxHearts(player) != levelRequired) return false;
        return true;
    }

    public boolean transmute(BlockBreakEvent event) {
        if (!canTransmute(event)) return false;

        var player = event.getPlayer();
        var playerHealthManager = PlayerHealthManager.getInstance();
        var itemHeld = player.getInventory().getItemInMainHand();

        event.setDropItems(false);

        lightningStrikeBlock(event.getBlock());

        ItemMeta itemHeldMeta = itemHeld.getItemMeta();
        if (itemHeldMeta instanceof Damageable){
            //Bukkit.getLogger().info("Damageable");
            Damageable damagable = (Damageable) itemHeldMeta;
            int currentDamage = damagable.getDamage();
            damagable.setDamage(currentDamage + ConfigHelper.getTransmuteItemDamage());
            itemHeld.setItemMeta(damagable);
            //player.updateInventory();
        }

        playerHealthManager.addHearts(player, heartsGained);
        new PlayerMessageManager(player).sendNextHeartMessage();

        return true;
    }

}
