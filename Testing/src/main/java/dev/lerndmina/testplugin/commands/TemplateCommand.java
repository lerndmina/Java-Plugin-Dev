package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TemplateCommand extends AbstractCommand {
    private Main main; // Define main class

    public TemplateCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = isPlayerAndHasPermission(sender, "wild.command.REPLACEME"); // Check if player is online and has permission
        if (player != null) {
            sendMessage(player, "&cREPLACEME"); // Send message to player
        }
        return false;
    }
}