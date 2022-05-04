package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuCommand extends AbstractCommand {

    public MenuCommand(Main main) { // Import main for use in this class
        super(main);
    }


    boolean[] menuPositionUsed;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = isPlayerAndHasPermission(sender, "wild.command.menu", false);
        if (player == null) { // Execute the command and run this code.
            return false;
        }
        int menurows = 3;
        menuPositionUsed = uiArray(menurows);

        // Generate an array with invSize
        // Each array position is filled with an int that matches it's position


        String invTitle = parseColor("&6&lMain Menu");
        Inventory menu = Bukkit.createInventory(player, menuSize(menurows), invTitle);

        // Close menu
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(parseColor("&cClose Menu"));
        close.setItemMeta(closeMeta);
        addButtonToUI(menu, close, 0, menuPositionUsed);

        // Fly feather
        ItemStack fly = new ItemStack(Material.FEATHER);
        ItemMeta flyMeta = fly.getItemMeta();
        flyMeta.setDisplayName(parseColor("&eToggle Flight"));
        flyMeta.setLore(Arrays.asList(
                parseColor("&fToggle flight on or off"),
                parseColor("&fReach for the skies!")
        ));
        fly.setItemMeta(flyMeta);
        addButtonToUI(menu, fly, 11, menuPositionUsed);


        // Close menu
        ItemStack randombutton = new ItemStack(Material.ENDER_PEARL);
        ItemMeta randombuttonMeta = close.getItemMeta();
        randombuttonMeta.setDisplayName(parseColor("&cButton"));
        randombutton.setItemMeta(randombuttonMeta);
        addButtonToUI(menu, randombutton, 15, menuPositionUsed);


        // Background
        ItemStack bg = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta bgMeta = bg.getItemMeta();
        bgMeta.setDisplayName(parseColor("&f"));
        bg.setItemMeta(bgMeta);
        for (int loopCount = 0; loopCount < menuPositionUsed.length; loopCount++) {
            if (!menuPositionUsed[loopCount]) {
                menu.setItem(loopCount, bg);
            }
        }
        player.openInventory(menu);
        return true;
    }
}