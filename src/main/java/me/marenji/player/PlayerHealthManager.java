package me.marenji.player;

import me.marenji.TransmutePlugin;
import me.marenji.util.ConfigHelper;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class PlayerHealthManager {

    private static PlayerHealthManager single_instance = null;

    private final int minHearts;
    private final int maxHearts;
    private final int defaultHeartChange;
    private final int heartLossPenaltyCooldown;

    private HashMap<String, List<PotionEffect>> playerEffectsPreDeath = new HashMap<>();

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

    public boolean addHearts(Player player, int hearts) {
        return changeHearts(player, hearts);
    }

    public boolean subtractHearts(Player player, int hearts) {
        return changeHearts(player, -hearts);
    }

    public boolean applyDefaultHeartLoss(Player player) {
        return subtractHearts(player, defaultHeartChange);
    }

    public boolean applyDefaultHeartGain(Player player) {
        return addHearts(player, defaultHeartChange);
    }

    public int getMaxHearts(Player player) {
        // rounded down i.e. 3.845 is 3
        return healthToHearts(getMaxHealth(player));
    }

    public void handleDeath(Player player) {
        var toPlayer = new PlayerMessageManager(player);
        rememberPersistingPotionEffects(player);

        if (isPlayerImmuneToPenalty(player)) {
            toPlayer.message("You have died. Your max health penalty is on cooldown, so you have not lost a heart");
            return;
        }
        if (applyDefaultHeartLoss(player)) {
            toPlayer.message("You have died. You have returned, but you have one less heart");
            return;
        }
        toPlayer.message("You have died. You have no hearts left, so you have not lost a heart");
    }

    private void rememberPersistingPotionEffects(Player player) {
        var playerId = player.getUniqueId().toString();
        if (!playerEffectsPreDeath.containsKey(playerId)) {
            playerEffectsPreDeath.put(playerId, new ArrayList<>());
        }
        var effectList = playerEffectsPreDeath.get(playerId);
        effectList.clear();
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getType() != PotionEffectType.UNLUCK) continue;
            effectList.add(effect);
        }
    }

    private PotionEffect getPreDeathBadLuckStatusEffect(Player player) {
        var playerId = player.getUniqueId().toString();
        if (!playerEffectsPreDeath.containsKey(playerId)) {
            return null;
        }
        var effectList = playerEffectsPreDeath.get(playerId);
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getType() != PotionEffectType.UNLUCK) continue;
            return effect;
        }
        return null;
    }

    public void handleRespawn(Player player) {
        applyPenaltyImmunity(player);
        applyDamageImmunity(player);
    }

    public void applyPenaltyImmunity(Player player) {
        var toPlayer = new PlayerMessageManager(player);
        var duration = heartLossPenaltyCooldown * 60 * 20;

        var preExistingBadLuckEffect = getPreDeathBadLuckStatusEffect(player);
        if (preExistingBadLuckEffect != null) {
            duration = preExistingBadLuckEffect.getDuration();
            toPlayer.message("The heart loss penalty cooldown has not reset after death");
        } else {
            toPlayer.message("Heart loss penalty cooldown has been applied for 3 minutes");
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, duration, 0));
    }

    public void applyDamageImmunity(Player player) {
        player.addPotionEffect(new PotionEffect(
                PotionEffectType.DAMAGE_RESISTANCE, 5 * 20, 4
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