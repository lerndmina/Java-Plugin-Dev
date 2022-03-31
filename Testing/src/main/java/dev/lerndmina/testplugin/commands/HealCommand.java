package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH;

public class HealCommand extends AbstractCommand {

    public HealCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = isPlayerAndHasPermission(sender, "wild.command.heal", false); // Check if player is online and has permission
        if (player != null) {
            double playerMaxHP = player.getAttribute(GENERIC_MAX_HEALTH).getValue(); // Get player's max hp

            if ((player.getHealth() >= playerMaxHP) && (player.getFoodLevel() >= 20) && (player.getRemainingAir() >= 300)) { // Check if the player's health & food is greater than or equal to max
                sendMessage(player, main.getConfig().getString("heal.fullhealth"));
            } else { // If the player's health is less than 20
                player.setHealth(playerMaxHP);
                player.setFoodLevel(20);
                player.setSaturation(20);
                player.setRemainingAir(300);
                sendMessage(player, main.getConfig().getString("heal.healsuccess"));
                sendConsoleInfo(player.getName() + " has been healed");
            }
        }
        return false;
    }
}
