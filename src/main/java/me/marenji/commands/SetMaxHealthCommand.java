package me.marenji.commands;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerHealthManager;
import me.marenji.player.PlayerMessageManager;
import me.marenji.util.ConfigHelper;
import me.marenji.util.PermissionsHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMaxHealthCommand implements CommandExecutor {

    private TransmutePlugin plugin;
    private PlayerHealthManager healthManager;

    public SetMaxHealthCommand() {
        this.plugin = TransmutePlugin.getInstance();
        this.healthManager = PlayerHealthManager.getInstance();
        plugin.getCommand("maxhealth").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        var toSender = new PlayerMessageManager(sender);

        if ( !PermissionsHelper.isSenderAdmin(sender) ) {
            toSender.message(ConfigHelper.getTextNotAdmin());
            return false;
        }

        // Getting command arguments
        String playerString = null;
        String heartsString = null;
        if ( args.length == 1 ) {
            heartsString = args[0];
        } else if ( args.length >= 2) {
            playerString = args[0];
            heartsString = args[1];
        }

        // no arguments specified
        if ( heartsString == null ) {
            toSender.message(ConfigHelper.getTextMaxHealthInvalidArgs());
            return false;
        }

        int hearts;
        try {
            hearts = Integer.parseInt(heartsString);
        } catch (Exception e) {
            toSender.message(ConfigHelper.getTextMaxHealthInvalidArgs());
            return false;
        }

        Player targetPlayer = null;
        if ( playerString == null) { // no player specified
            if ( sender instanceof Player ) {
                targetPlayer = (Player)sender;
                playerString = targetPlayer.getName();
            } else {
                playerString = "Unknown";
            }
        } else {
            targetPlayer = Bukkit.getPlayerExact(playerString);
        }
        if ( targetPlayer == null ) {
            toSender.message("The player with the name " + playerString + " cannot be found on the server.");
            return false;
        }
        var toTarget = new PlayerMessageManager(targetPlayer);

        var success = healthManager.setMaxHearts(targetPlayer, hearts);
        if ( success ) {
            toSender.message("The max health has been set for" + playerString);
            toTarget.message("Your max health has been changed");
            toTarget.sendNextHeartMessage();
            return true;
        } else {
            toSender.message(ConfigHelper.getTextMaxHealthInvalidHearts());
            return false;
        }

    }

}
