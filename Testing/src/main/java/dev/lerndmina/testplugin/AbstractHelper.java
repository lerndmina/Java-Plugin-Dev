package dev.lerndmina.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public abstract class AbstractHelper {
    protected final Main main; // Define main class

    public AbstractHelper(Main main) {
        this.main = main;
    }

    // Grab things from the config file
    public String configString(String path) {
        return main.getConfig().getString(path);
    }

    public Integer configInt(String path) {
        return main.getConfig().getInt(path);
    }

    public Boolean configBool(String path) {
        return main.getConfig().getBoolean(path);
    }

    // Check for permission
    public boolean hasPermission(Player player, String permission) {
        if (player.hasPermission(permission)) { // If player has permission return the player
            return true;
        } else { // If player doesn't have permission return null
            sendMessage(player, main.getConfig().getString("no-permission"));
            sendMessage(player, "&cpermission required &r>> &c" + permission);
            return false;
        }
    }

    public void debugPlayerMessage(Player p, String message) {
        if (main.debug) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lDEBUG &4&l>> &r" + message));
        }
    }


    // !!! ITEM CHECKS !!!
    public boolean hasItemInHand(Player p, Material material) {
        return p.getInventory().getItemInMainHand().getType().equals(material);
        // Returns true if the item in the players hand is the same as the material
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("prefix") + message));
    }

    public void sendConsoleInfo(String message) {
        main.getLogger().info(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void sendConsoleWarn(String message) {
        main.getLogger().warning(ChatColor.translateAlternateColorCodes('&', message));
    }
}
