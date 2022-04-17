package me.marenji.util;

import me.marenji.TransmutePlugin;
import me.marenji.transmutables.HeartTransmutable;
import me.marenji.transmutables.TreasureTransmutable;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Map;


public final class ConfigHelper {

    public static Iterable<HeartTransmutable> getHeartTransmutables()
    {
        var mapList = getMapListFromConfig("heart_transmutables");
        var heartTransmutableList = new ArrayList<HeartTransmutable>();
        for (var map: mapList) {
            var material = Material.valueOf((String)map.get("material"));
            var levelRequired = (int)map.get("levelRequired");
            var heartsGained = (int)map.get("heartsGained");
            var displayName = (String)map.get("displayName");
            var transmutable = new HeartTransmutable(
                material,
                displayName,
                levelRequired,
                heartsGained
            );
            Bukkit.getLogger().info("transmutable: material=" + material.toString() + ", levelRequired=" + levelRequired + ", heartsGained=" + heartsGained);
            heartTransmutableList.add(transmutable);
        }

        return heartTransmutableList;
    }

    public static Iterable<TreasureTransmutable> getTreasureTransmutables() {
        var mapList = getMapListFromConfig("treasure_transmutables");
        var treasureTransmutableList = new ArrayList<TreasureTransmutable>();
        for (var map: mapList) {
            var material = Material.valueOf((String)map.get("material"));
            var quality = (int)map.get("quality");
            var displayName = (String)map.get("displayName");
            var transmutable = new TreasureTransmutable(
                material,
                displayName,
                quality
            );
            Bukkit.getLogger().info("transmutable: material=" + material.toString() + ", quality=" + quality);
            treasureTransmutableList.add(transmutable);
        }

        return treasureTransmutableList;
    }

    public static int getMinHearts() {
        return getInt("minhearts");
    }

    public static int getMaxHearts() {
        return getInt("maxhearts");
    }

    public static int getDefaultHeartChange() {
        return getInt("defaultheartchange");
    }

    public static int getHeartPenaltyCooldown() {
        return getInt("heartpenaltycooldown");
    }

    public static int getTransmuteItemDamage() {
        return getInt("transmuteitemdamage");
    }

    public static String getTextNotAdmin() {
        return getString("notadmin_message");
    }

    public static String getTextMaxHealthInvalidArgs() {
        return getString("maxhealth_invalidargs_message");
    }

    public static String getTextMaxHealthAllInvalidArgs() {
        return getString("maxhealthall_invalidargs_message");
    }

    public static String getTextMaxHealthInvalidHearts() {
        return getString("maxhealth_invalidhearts_message");
    }

    public static String getTextPlayerJoined() {
        return getString("player_joined_message");
    }

    private static String getString(String key) {
        var stringValue = TransmutePlugin
                .getInstance()
                .getConfig()
                .getString(key);
        return stringValue;
    }

    private static int getInt(String key) {
        var integerValue = TransmutePlugin
                .getInstance()
                .getConfig()
                .getInt(key);
        return integerValue;
    }

    private static Iterable<Map<?, ?>> getMapListFromConfig(String key) {
        var objectList = TransmutePlugin
                .getInstance()
                .getConfig()
                .getMapList(key);
        return objectList;
    }

}
