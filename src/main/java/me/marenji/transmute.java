package me.marenji;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class transmute extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Transmute plugin started");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Transmute plugin stopped");
    }
}
