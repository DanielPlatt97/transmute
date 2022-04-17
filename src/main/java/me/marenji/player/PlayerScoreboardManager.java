package me.marenji.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerScoreboardManager {

    private Player player;
    private final String netherTag = "nether";

    public PlayerScoreboardManager(Player player) {
        this.player = player;
    }

    public boolean getCanEnterNether() {
        return hasScoreboardTag(netherTag);
    }

    public boolean setCanEnterNether() {
        return giveScoreboardTag(netherTag);
    }

    private boolean giveScoreboardTag(String tag) {
        if (hasScoreboardTag(tag)) return false;
        var added = player.addScoreboardTag(tag);
        return added;
    }

    private boolean removeScoreboardTag(String tag) {
        if (!hasScoreboardTag(tag)) return false;
        var removed = player.removeScoreboardTag(tag);
        return removed;
    }

    private boolean hasScoreboardTag(String tag) {
        var tags = player.getScoreboardTags();
        var hasTag = tags.contains(tag);
        return hasTag;
    }

}
