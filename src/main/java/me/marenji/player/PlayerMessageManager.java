package me.marenji.player;

import me.marenji.transmutables.TransmutableManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class PlayerMessageManager {

    private CommandSender sender;

    public PlayerMessageManager(CommandSender player) {
        this.sender = player;
    }

    public void sendNextHeartMessage() {
        var message = "You must destroy a new type of block with a Golden Pickaxe to gain another heart.";
        if (sender instanceof  Player) {
            var currentHearts = PlayerHealthManager.getInstance().getMaxHearts((Player)sender);
            message = TransmutableManager.getInstance().getNextHeartTransmutableMessage(currentHearts);
        }
        message(message);
    }

    public void message(String text) {
        sender.sendMessage(
            ChatColor.translateAlternateColorCodes('&', text)
        );
    }

}