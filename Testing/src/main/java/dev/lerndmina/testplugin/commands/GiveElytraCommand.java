package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractCommand;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GiveElytraCommand extends AbstractCommand {

    public GiveElytraCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = isPlayerAndHasPermission(sender, "wild.command.elytra", false); // Check if player is online and has permission
        if (player != null) {
            ItemStack elytra = new ItemStack(Material.ELYTRA);
            ItemMeta elytraMeta = elytra.getItemMeta();
            elytraMeta.setUnbreakable(true);
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§eCancels Fall Damage");
            elytraMeta.setLore(lore);
            elytra.setItemMeta(elytraMeta);
            player.getInventory().addItem(elytra);
            sendMessage(player, "&aYou have been given an Elytra!");
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
