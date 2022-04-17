package me.marenji.commands;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerHealthManager;
import me.marenji.player.PlayerMessageManager;
import me.marenji.util.ChatHelper;
import me.marenji.util.ConfigHelper;
import me.marenji.util.PermissionsHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetMaxHealthAllCommand implements CommandExecutor {

    private TransmutePlugin plugin;
    private PlayerHealthManager healthManager;

    public SetMaxHealthAllCommand() {
        this.plugin = TransmutePlugin.getInstance();
        this.healthManager = PlayerHealthManager.getInstance();
        plugin.getCommand("maxhealthall").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        var messager = new PlayerMessageManager(sender);

        if ( !PermissionsHelper.isSenderAdmin(sender) ) {
            messager.message(ConfigHelper.getTextNotAdmin());
            return false;
        }

        // Getting command arguments
        String heartsString = null;
        if ( args.length >= 1) {
            heartsString = args[0];
        }

        // no arguments specified
        if ( heartsString == null ) {
            messager.message(ConfigHelper.getTextMaxHealthAllInvalidArgs());
            return false;
        }

        int hearts;
        try {
            hearts = Integer.parseInt(heartsString);
        } catch (Exception e) {
            messager.message(ConfigHelper.getTextMaxHealthAllInvalidArgs());
            return false;
        }

        var success = healthManager.setMaxHeartsAllPlayers(hearts);
        if ( success ) {
            Bukkit.broadcastMessage("All online players have had their max health set to a new value");
            return true;
        } else {
            messager.message(ConfigHelper.getTextMaxHealthInvalidHearts());
            return false;
        }
    }

}
