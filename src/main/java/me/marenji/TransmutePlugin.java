package me.marenji;

import me.marenji.commands.SetMaxHealthAllCommand;
import me.marenji.commands.SetMaxHealthCommand;
import me.marenji.listeners.DeathListener;
import me.marenji.listeners.GoldenFoodListener;
import me.marenji.listeners.JoinListener;
import me.marenji.listeners.RespawnListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TransmutePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Transmute plugin started");

        saveDefaultConfig();
        new SetMaxHealthCommand(this);
        new SetMaxHealthAllCommand(this);
        new JoinListener(this);
        new DeathListener(this);
        new GoldenFoodListener(this);
        new RespawnListener(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Transmute plugin stopped");
    }
}
