package me.marenji.transmutables;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Random;

public final class TreasureManager {

    private static TreasureManager single_instance = null;

    private Map<Material, ItemStack[]> treasuresByMaterial;

    public ItemStack getRandomItemStackForMaterial(Material material) {
        if (!treasuresByMaterial.containsKey(material)) {
            throw new IllegalArgumentException("Material not implemented");
        }
        var items = treasuresByMaterial.get(material);
        var randomIndex = new Random().nextInt(items.length);
        var randomItem = items[randomIndex];
        return randomItem;
    }

    public Map<Material, ItemStack[]> getTreasuresByMaterial() {
        return treasuresByMaterial;
    }

    public static TreasureManager getInstance()
    {
        if (single_instance == null) {
            single_instance = new TreasureManager();
        }
        return single_instance;
    }

    private TreasureManager()
    {
        var fortuneIronPick = new ItemStack(Material.IRON_PICKAXE, 1);
        fortuneIronPick.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);
        var mendingGoldSword = new ItemStack(Material.GOLDEN_SWORD, 1);
        mendingGoldSword.addEnchantment(Enchantment.MENDING, 1);
        var copperList = new ItemStack[]{
            new ItemStack(Material.REDSTONE, 10),
            new ItemStack(Material.LAPIS_LAZULI, 10),
            new ItemStack(Material.GUNPOWDER, 10),
            new ItemStack(Material.ARROW, 20),
            new ItemStack(Material.FEATHER, 10),
            new ItemStack(Material.FLINT, 20),
            new ItemStack(Material.BREAD, 3),
            new ItemStack(Material.GLOWSTONE_DUST, 10),
            new ItemStack(Material.COAL, 18),
            new ItemStack(Material.STICK, 32),
            new ItemStack(Material.BRICK_WALL, 64),
            new ItemStack(Material.CLAY_BALL, 12),
            new ItemStack(Material.DIAMOND, 1),
            new ItemStack(Material.PUMPKIN, 3),
            new ItemStack(Material.FOX_SPAWN_EGG, 1),
            new ItemStack(Material.IRON_INGOT, 9),
            new ItemStack(Material.GOLD_INGOT, 6),
            new ItemStack(Material.LEATHER, 7),
            new ItemStack(Material.GRASS_BLOCK, 3),
            new ItemStack(Material.SILVERFISH_SPAWN_EGG, 3),
            fortuneIronPick,
            mendingGoldSword,
            new ItemStack(Material.DONKEY_SPAWN_EGG, 1),
            new ItemStack(Material.HORSE_SPAWN_EGG, 1),
            new ItemStack(Material.CAKE, 1),
            new ItemStack(Material.IRON_PICKAXE, 1),
            new ItemStack(Material.IRON_AXE, 1),
            new ItemStack(Material.IRON_HELMET, 1),
            new ItemStack(Material.IRON_BOOTS, 1),
            new ItemStack(Material.CROSSBOW, 1),
            new ItemStack(Material.HOPPER, 1),
            new ItemStack(Material.MINECART, 1),
            new ItemStack(Material.EXPERIENCE_BOTTLE, 6),
            new ItemStack(Material.BLAZE_ROD, 2),
            new ItemStack(Material.ENDER_EYE, 2),
            new ItemStack(Material.ORANGE_BED, 1),
            new ItemStack(Material.DIAMOND_SWORD, 1),
            new ItemStack(Material.TNT, 3),
            new ItemStack(Material.COPPER_BLOCK, 2),
            new ItemStack(Material.LAPIS_BLOCK, 2),
            new ItemStack(Material.REDSTONE_BLOCK, 2),
            new ItemStack(Material.CREEPER_SPAWN_EGG, 1),
            new ItemStack(Material.ZOMBIE_SPAWN_EGG, 5),
            new ItemStack(Material.SKELETON_SPAWN_EGG, 3),
            new ItemStack(Material.VILLAGER_SPAWN_EGG, 1),
            new ItemStack(Material.EMERALD, 5),
            new ItemStack(Material.BEEHIVE, 1),
            new ItemStack(Material.MOOSHROOM_SPAWN_EGG, 1),
            new ItemStack(Material.LEATHER_HORSE_ARMOR, 1),
        };
        treasuresByMaterial = Map.of(
            Material.HAY_BLOCK,
            new ItemStack[]{
                new ItemStack(Material.OAK_SAPLING, 3),
                new ItemStack(Material.SPRUCE_SAPLING, 3),
                new ItemStack(Material.ACACIA_SAPLING, 3),
                new ItemStack(Material.BIRCH_SAPLING, 3),
                new ItemStack(Material.DARK_OAK_SAPLING, 3),
                new ItemStack(Material.JUNGLE_SAPLING, 3),
                new ItemStack(Material.BAMBOO_SAPLING, 3),
                new ItemStack(Material.CARROT, 1),
                new ItemStack(Material.POTATO, 1),
                new ItemStack(Material.POISONOUS_POTATO, 1),
                new ItemStack(Material.EGG, 5),
                new ItemStack(Material.BAT_SPAWN_EGG, 1),
                new ItemStack(Material.IRON_HOE, 1),
                new ItemStack(Material.GOLDEN_HOE, 1),
                new ItemStack(Material.GOLDEN_APPLE, 1),
                new ItemStack(Material.BEETROOT_SEEDS, 3),
                new ItemStack(Material.MELON_SEEDS, 3),
                new ItemStack(Material.PUMPKIN_SEEDS, 3),
                new ItemStack(Material.ARROW, 20),
                new ItemStack(Material.FEATHER, 10),
                new ItemStack(Material.FLINT, 20),
                new ItemStack(Material.BREAD, 3),
                new ItemStack(Material.SHEEP_SPAWN_EGG, 1),
                new ItemStack(Material.COW_SPAWN_EGG, 1),
                new ItemStack(Material.MOOSHROOM_SPAWN_EGG, 1),
                new ItemStack(Material.CHICKEN_SPAWN_EGG, 1),
                new ItemStack(Material.SUGAR_CANE, 3),
                new ItemStack(Material.EMERALD, 1),
                new ItemStack(Material.BRICK_WALL, 64),
                new ItemStack(Material.WATER_BUCKET, 1),
                new ItemStack(Material.GRASS_BLOCK, 3),
                new ItemStack(Material.LEATHER, 8),
                new ItemStack(Material.BROWN_MUSHROOM, 3),
                new ItemStack(Material.RED_MUSHROOM, 3),
                new ItemStack(Material.DONKEY_SPAWN_EGG, 1),
                new ItemStack(Material.HORSE_SPAWN_EGG, 1),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 2),
                new ItemStack(Material.SPIDER_EYE, 1),
                new ItemStack(Material.BONE, 1),
                new ItemStack(Material.YELLOW_BED, 1),
                new ItemStack(Material.BEEHIVE, 1),
                new ItemStack(Material.TNT, 1),
                new ItemStack(Material.BONE_MEAL, 64),
                new ItemStack(Material.HAY_BLOCK, 2),
                new ItemStack(Material.TURTLE_EGG, 1),
                new ItemStack(Material.DIAMOND_HOE, 1),
                new ItemStack(Material.DIAMOND_HOE, 1),
                new ItemStack(Material.LEATHER_HORSE_ARMOR, 1),
            },
            Material.COAL_BLOCK,
            new ItemStack[]{
                new ItemStack(Material.REDSTONE, 10),
                new ItemStack(Material.REDSTONE, 10),
                new ItemStack(Material.REDSTONE, 10),
                new ItemStack(Material.LAPIS_LAZULI, 10),
                new ItemStack(Material.GUNPOWDER, 10),
                new ItemStack(Material.GUNPOWDER, 10),
                new ItemStack(Material.GUNPOWDER, 10),
                new ItemStack(Material.ARROW, 20),
                new ItemStack(Material.FEATHER, 10),
                new ItemStack(Material.FLINT, 20),
                new ItemStack(Material.BREAD, 3),
                new ItemStack(Material.GLOWSTONE_DUST, 10),
                new ItemStack(Material.SUGAR, 10),
                new ItemStack(Material.COAL, 18),
                new ItemStack(Material.STICK, 32),
                new ItemStack(Material.BRICK, 1),
                new ItemStack(Material.CLAY_BALL, 12),
                new ItemStack(Material.DIAMOND, 1),
                new ItemStack(Material.MILK_BUCKET, 1),
                new ItemStack(Material.ZOMBIE_SPAWN_EGG, 1),
                new ItemStack(Material.SKELETON_SPAWN_EGG, 1),
                new ItemStack(Material.ZOMBIE_VILLAGER_SPAWN_EGG, 1),
                new ItemStack(Material.DIRT, 64),
                new ItemStack(Material.NETHERRACK, 32),
                new ItemStack(Material.SAND, 46),
                new ItemStack(Material.BRICK_WALL, 64),
                new ItemStack(Material.LAVA_BUCKET, 1),
                new ItemStack(Material.LANTERN, 1),
                new ItemStack(Material.BAT_SPAWN_EGG, 1),
                new ItemStack(Material.CHICKEN_SPAWN_EGG, 1),
                new ItemStack(Material.PARROT_SPAWN_EGG, 1),
                new ItemStack(Material.CANDLE, 3),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 4),
                new ItemStack(Material.BLAZE_ROD, 1),
                new ItemStack(Material.ENDER_EYE, 1),
                new ItemStack(Material.BLACK_BED, 1),
                new ItemStack(Material.TNT, 2),
                new ItemStack(Material.COAL_BLOCK, 2),
                new ItemStack(Material.EMERALD, 2),
                new ItemStack(Material.LEATHER_HORSE_ARMOR, 1),
            },
            Material.COPPER_BLOCK,
            copperList,
            Material.EXPOSED_COPPER,
            copperList,
            Material.WEATHERED_COPPER,
            copperList,
            Material.OXIDIZED_COPPER,
            copperList,
            Material.LAPIS_BLOCK,
            copperList,
            Material.REDSTONE_BLOCK,
            copperList,
            Material.GOLD_BLOCK,
            new ItemStack[]{
                new ItemStack(Material.VILLAGER_SPAWN_EGG, 1),
                new ItemStack(Material.VILLAGER_SPAWN_EGG, 1),
                new ItemStack(Material.VILLAGER_SPAWN_EGG, 1),
                new ItemStack(Material.REDSTONE, 30),
                new ItemStack(Material.LAPIS_LAZULI, 20),
                new ItemStack(Material.GUNPOWDER, 20),
                new ItemStack(Material.ARROW, 30),
                new ItemStack(Material.FEATHER, 15),
                new ItemStack(Material.BREAD, 12),
                new ItemStack(Material.GLOWSTONE_DUST, 30),
                new ItemStack(Material.COAL, 30),
                new ItemStack(Material.CLAY_BALL, 64),
                new ItemStack(Material.BRICK_WALL, 64),
                new ItemStack(Material.LAVA_BUCKET, 1),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 9),
                new ItemStack(Material.BLAZE_ROD, 3),
                new ItemStack(Material.ENDER_EYE, 3),
                new ItemStack(Material.YELLOW_BED, 1),
                new ItemStack(Material.TNT, 5),
                new ItemStack(Material.BONE, 10),
                new ItemStack(Material.GOLD_BLOCK, 2),
                new ItemStack(Material.CREEPER_SPAWN_EGG, 1),
                new ItemStack(Material.ZOMBIE_SPAWN_EGG, 5),
                new ItemStack(Material.SKELETON_SPAWN_EGG, 3),
                new ItemStack(Material.SKELETON_SPAWN_EGG, 3),
                new ItemStack(Material.BLAZE_SPAWN_EGG, 1),
                new ItemStack(Material.GHAST_SPAWN_EGG, 1),
                new ItemStack(Material.EMERALD, 18),
                new ItemStack(Material.ANCIENT_DEBRIS, 1),
                new ItemStack(Material.DIAMOND, 2),
                new ItemStack(Material.FLINT, 30),
                new ItemStack(Material.MAGMA_CREAM, 7),
                new ItemStack(Material.GHAST_TEAR, 5),
                new ItemStack(Material.TURTLE_EGG, 3),
                new ItemStack(Material.DIAMOND_SWORD, 1),
                new ItemStack(Material.DIAMOND_PICKAXE, 1),
                new ItemStack(Material.DIAMOND_AXE, 1),
                new ItemStack(Material.GOLDEN_HORSE_ARMOR, 1),
            }
        );
    }

}
