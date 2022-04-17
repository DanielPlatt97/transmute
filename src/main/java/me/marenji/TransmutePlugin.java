package me.marenji;

import me.marenji.commands.SetMaxHealthAllCommand;
import me.marenji.commands.SetMaxHealthCommand;
import me.marenji.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TransmutePlugin extends JavaPlugin {

    private static TransmutePlugin single_instance = null;

    public TransmutePlugin() {
        super();
        initStaticSingleInstance();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Transmute plugin started");

        saveDefaultConfig();
        new SetMaxHealthCommand();
        new SetMaxHealthAllCommand();
        new JoinListener();
        new DeathListener();
        new RespawnListener();
        new BlockBreakListener();
        new NetherPortalListener();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Transmute plugin stopped");
    }

    public static TransmutePlugin getInstance()
    {
        if (single_instance == null) {
            throw new AssertionError(
                "The singleton has not been initialised, call initStaticSingleInstance first"
            );
        }
        return single_instance;
    }

    private void initStaticSingleInstance() {
        if (single_instance != null)
        {
            throw new AssertionError("The singleton was already initialised");
        }
        single_instance = this;
    }

}
