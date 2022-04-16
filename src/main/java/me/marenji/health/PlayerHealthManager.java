package me.marenji.health;

import me.marenji.util.ConfigHelper;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class PlayerHealthManager {

    private static PlayerHealthManager single_instance = null;

    private final int minHearts;
    private final int maxHearts;
    private final int defaultHeartChange;
    private final int heartLossPenaltyCooldown;

    public PlayerHealthManager() {
        minHearts = ConfigHelper.getMinHearts();
        maxHearts = ConfigHelper.getMaxHearts();
        defaultHeartChange = ConfigHelper.getDefaultHeartChange();
        heartLossPenaltyCooldown = ConfigHelper.getHeartPenaltyCooldown();
    }

    public static PlayerHealthManager getInstance()
    {
        if (single_instance == null) {
            single_instance = new PlayerHealthManager();
        }
        return single_instance;
    }

    public boolean setMaxHeartsAllPlayers(int hearts) {
        var success = false;
        if ( isValidHeartAmount(hearts) ) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                setMaxHearts(player, hearts);
            }
            success = true;
        }
        return success;
    }

    public boolean setMaxHearts(Player player, int hearts) {
        var success = false;
        if ( isValidHeartAmount(hearts) ) {
            setMaxHealth(player, heartsToHealth(hearts));
            success = true;
        }
        return success;
    }

    public boolean changeHearts(Player player, int hearts) {
        var attribute = getMaxHealthAttribute(player);
        var currentHearts = healthToHearts(attribute.getBaseValue());
        var newHearts = currentHearts + hearts;
        if ( isValidHeartAmount( newHearts ) ) {
            attribute.setBaseValue(heartsToHealth(newHearts));
            return true;
        } else {
            return false;
        }
    }

    public boolean applyDefaultHeartLoss(Player player) {
        return changeHearts(player, -defaultHeartChange);
    }

    public boolean applyDefaultHeartGain(Player player) {
        return changeHearts(player, defaultHeartChange);
    }

    public int getMaxHearts(Player player) {
        // rounded down i.e. 3.845 is 3
        return healthToHearts(getMaxHealth(player));
    }

    public void applyPenaltyImmunity(Player player) {
        player.addPotionEffect(new PotionEffect(
                PotionEffectType.UNLUCK, heartLossPenaltyCooldown * 60 * 20, 0
        ));
    }

    public boolean isPlayerImmuneToPenalty(Player player) {
        return player.hasPotionEffect(PotionEffectType.UNLUCK);
    }

    public void setPlayerHealthToMaxHealth(Player player) {
        var health = getMaxHealth(player);
        player.setHealth(health);
    }

    private int healthToHearts(double health) {
        return (int)(health/2);
    }

    private double heartsToHealth(int hearts) {
        return (double)hearts*2;
    }

    private void setMaxHealth(Player player, double health) {
        var attribute = getMaxHealthAttribute(player);
        attribute.setBaseValue(health);
    }

    private double getMaxHealth(Player player) {
        var attribute = getMaxHealthAttribute(player);
        return attribute.getBaseValue();
    }

    private AttributeInstance getMaxHealthAttribute(Player player) {
        return player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
    }

    public boolean isValidHeartAmount(int hearts) {
        return (hearts >= minHearts && hearts <= maxHearts);
    }

}