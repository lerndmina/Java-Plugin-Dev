package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class DebugCommand extends AbstractCommand {
    private Main main; // Define main class

    public DebugCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = isPlayerAndHasPermission(sender, "wild.command.debug"); // Check if player is online and has permission
        if (player != null) {
            main.debug = !main.debug; // Toggle debug
            sendMessage(player, "&aDebug is now " + main.debug); // Send message to player
        }
        return false;
    }
}
