package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GiveElytraCommand extends AbstractCommand {

    public GiveElytraCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = isPlayerAndHasPermission(sender, "wild.command.elytra"); // Check if player is online and has permission
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
}
