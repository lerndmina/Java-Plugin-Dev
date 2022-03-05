package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class GunToggleCommand extends AbstractCommand {
    private Main main; // Define main class

    public GunToggleCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = isPlayerAndHasPermission(sender, "wild.command.guntoggle"); // Check if player is online and has permission
        if (player != null) {
            if (args.length == 0){ // Check if there are no arguments
                if (isListed(player)) {
                    remove(player);
                    sendMessage(player, configString("guns.disabled-msg"));
                    sendConsoleInfo(player.getName() + " has disabled guns for them.");
                } else {
                    add(player);
                    sendMessage(player, configString("guns.enabled-msg"));
                    sendConsoleInfo(player.getName() + " has enabled guns for them.");
                }

            } else if (args.length == 1) { // Check if there is a second argument
                if (args[0].equalsIgnoreCase("on")) { // Turn on guns for player
                    add(player);
                    sendMessage(player, configString("guns.enabled-msg"));
                    sendConsoleInfo(player.getName() + " has enabled guns for them.");

                } else if (args[0].equalsIgnoreCase("off")) { // Turn off guns for player
                    remove(player);
                    sendMessage(player, configString("guns.disabled-msg"));
                    sendConsoleInfo(player.getName() + " has disabled guns for them.");

                } else { // If there is an argument, but it is not on or off then send error message
                    sendMessage(player, configString("guns.usage-msg"));
                }
            } else { // If there is more than one argument then send error message
                sendMessage(player, configString("guns.usage-msg"));
            }
        }

        return false;
    }
}
