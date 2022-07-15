package dev.lerndmina.exampleplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Exampleplugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin loaded!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
