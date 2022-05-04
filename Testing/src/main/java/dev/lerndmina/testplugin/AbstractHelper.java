package dev.lerndmina.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class AbstractHelper {
    protected final Main main; // Define main class

    public AbstractHelper(Main main) {
        this.main = main;
    }

    public static final int GUI_WIDTH = 9;

    // Grab things from the config file
    public String configString(String path) {
        return parseColor(main.getConfig().getString(path));
    }

    public Integer configInt(String path) {
        return main.getConfig().getInt(path);
    }

    public Boolean configBool(String path) {
        return main.getConfig().getBoolean(path);   
    }

    public void addButtonToUI(Inventory ui, ItemStack item, int position, boolean[] arr) {
        arr[position] = true;
        ui.setItem(position, item);
    }

    public boolean[] uiArray(int rows){
        return new boolean[rows * GUI_WIDTH];
    }

    public int menuSize(int rows){
        return rows * GUI_WIDTH;
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

    public String stringFromArgs(String[] arr, Integer arrPos) {
        StringBuilder builder = new StringBuilder();
        for (int index = arrPos; index < arr.length; index++) {
            builder.append(arr[index]).append(" ");
        }
        return parseColor(builder.toString());
    }

    public String parseColor(String content) {
        return (ChatColor.translateAlternateColorCodes('&', content));
    }

    public void debugPlayerMessage(Player p, String message) {
        if (main.debug) {
            p.sendMessage(parseColor("&c&lDEBUG &4&l>> &c" + message));
            sendConsoleInfo("&c&lDEBUG &4&l>> &c" + message);
        }
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage(parseColor(main.getConfig().getString("prefix") + message));
    }

    public void sendCleanMessage(Player player, String message) {
        player.sendMessage(parseColor(message));
    }


    public void sendConsoleInfo(String message) {
        main.getLogger().info(parseColor(message));
    }

    public void sendConsoleWarn(String message) {
        main.getLogger().warning(parseColor(message));
    }

    // !!! ITEM CHECKS !!!
    public boolean hasItemInHand(Player p, Material material) {
        return p.getInventory().getItemInMainHand().getType().equals(material);
        // Returns true if the item in the players hand is the same as the material
    }
}
