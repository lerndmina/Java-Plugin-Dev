package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCommand implements CommandExecutor {

    private Main main; // Define main class
    public HealCommand(Main main) { // Import main for use in this class
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) main.isPlayerAndHasPermission(sender,"");
        if (player != null) {
            if ((player.getHealth() >= 20) && (player.getFoodLevel() >= 20)) { // Check if the player's health/food is greater than or equal to 20
                main.sendMessage(player, main.getConfig().getString("heal.fullhealth"));
            } else { // If the player's health is less than 20
                player.setHealth(20);
                player.setFoodLevel(20);
                main.sendMessage(player, main.getConfig().getString("heal.healsuccess"));
                main.getLogger().info(player.getName() + " has been healed");
            }
        }
        return false;
    }
}
