package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {
    private Main main; // Define main class
    public TestCommand(Main main) { // Import main for use in this class
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) main.isPlayerAndHasPermission(sender, "test.command");
        if (player != null) {
            main.sendMessage(player, "&aTesting command");
        }

        return false;
    }
}
