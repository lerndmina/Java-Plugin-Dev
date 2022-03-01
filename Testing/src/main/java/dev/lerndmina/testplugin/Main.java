package dev.lerndmina.testplugin;

import dev.lerndmina.testplugin.commands.HealCommand;
import dev.lerndmina.testplugin.commands.TestCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin loaded pog");
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("test").setExecutor(new TestCommand(this));


        // Create config file and copy defaults
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin unloaded not pog");
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix") + message));
    }

    public void sendConsoleInfo(String message) {
        getLogger().info(ChatColor.translateAlternateColorCodes('&', message));
    }
    public void sendConsoleWarn(String message) {
        getLogger().warning(ChatColor.translateAlternateColorCodes('&', message));
    }


    public Object isPlayerAndHasPermission(Object sender, String permission) {
        if (sender instanceof Player) { // If sender is player check for permission
            Player player = (Player) sender;
            if (permission.equals("")) {// If there is no permission return the player
                return player;
            }
           if (player.hasPermission(permission)){ // If player has permission return the player
               return player;
           } else { // If player doesn't have permission return null
               sendMessage(player, getConfig().getString("no-permission"));
               return null;
           }
        } else { // If sender is console return null
            sendConsoleWarn("&cThis command can only be ran by a player.");
            return null;
        }
    }
}
