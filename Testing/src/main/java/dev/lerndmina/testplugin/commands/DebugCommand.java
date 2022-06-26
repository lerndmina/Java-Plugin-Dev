package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DebugCommand extends AbstractCommand {

    public DebugCommand(Main main) { // Import main for use in this class
        super(main);
        sendConsoleInfo("Debug instance loaded " + this.toString() + " main: " + main.toString() + " Main.Debug: " + main.debug);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = isPlayerAndHasPermission(sender, "wild.command.debug", true); // Check if player is online and has permission
        main.debug = !main.debug; // Toggle debug
        if (player != null) { // Handle player
            sendMessage(player, "&aDebug is now " + main.debug); // Send message to player
        } else { // Handle console
            sendConsoleInfo("Debug is now  " + main.debug);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
