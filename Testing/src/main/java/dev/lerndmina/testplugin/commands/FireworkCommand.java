package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FireworkCommand extends AbstractCommand {

    public FireworkCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = isPlayerAndHasPermission(sender, "wild.command.firework"); // Check if player is online and has permission
        if (player != null) {
            if (args.length == 0){
                main.fireworkEnabled = !main.fireworkEnabled; // Toggle firework
                sendMessage(player, (main.fireworkEnabled ? (configString("fireworks.enabled-msg")) : (configString("fireworks.disabled-msg"))));
            } else if (args.length == 1) { // Check if there is a second argument
                if (args[0].equalsIgnoreCase("on")) { // Turn on firework
                    main.fireworkEnabled = true;
                    sendMessage(player, configString("fireworks.enabled-msg"));
                } else if (args[0].equalsIgnoreCase("off")) { // Turn off firework
                    main.fireworkEnabled = false;
                    sendMessage(player, configString("fireworks.disabled-msg"));
                } else { // If there is an argument but it is not on or off then send error message
                    sendMessage(player, configString("fireworks.usage-msg"));
                }
            } else { // If there is more than one argument then send error message
                sendMessage(player, configString("fireworks.usage-msg"));
            }
        }

        return false;
    }
}
