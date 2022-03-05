package dev.lerndmina.testplugin;

import dev.lerndmina.testplugin.commands.*;
import dev.lerndmina.testplugin.events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin loaded pog");

        // Register Events
        Bukkit.getPluginManager().registerEvents(new JoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ToggleListener(this), this);
        Bukkit.getPluginManager().registerEvents(new FireworkListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GunListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ElytraFallListener(this), this);

        // Register commands
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("test").setExecutor(new TemplateCommand(this));
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("fireworks").setExecutor(new FireworkCommand(this));
        getCommand("guntoggle").setExecutor(new GunToggleCommand(this));
        getCommand("debug").setExecutor(new DebugCommand(this));
        getCommand("elytra").setExecutor(new GiveElytraCommand(this));

        // Create config file and copy defaults
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin unloaded not pog");
    }

    /*
     *
     * !!! VAR STORAGE !!!
     *
     */

    public boolean fireworkEnabled = false; // Global firework toggle
    public List<UUID> gunsEnabled = new ArrayList<>(); // List of people who have guns turned on

    // TODO: convert to list so that each player can have their own toggle
    public boolean debug = false;
}