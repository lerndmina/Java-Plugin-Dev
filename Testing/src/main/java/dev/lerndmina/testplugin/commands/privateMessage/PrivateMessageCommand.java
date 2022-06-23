package dev.lerndmina.testplugin.commands.privateMessage;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrivateMessageCommand extends AbstractCommand {

    public static final int REQUIRED_ARGS = 2;

    public PrivateMessageCommand(Main main) { // Import main for use in this class
        super(main);
    }

    //TODO: /pm <player> <message>

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = isPlayerAndHasPermission(sender, "wild.command.pm", false);
        if (player != null) { // Execute the command and run this code.
            String usage = "&eUsage: /pm <player> <message>"; // Set usage
            if (args.length >= REQUIRED_ARGS) {
                Player target = Bukkit.getPlayerExact(args[0]); // checks if online
                if (target != null) { // Check if the player exists
                    if (target.equals(player)){ // Check if target is different from sender
                        sendMessage(player, "You cannot send a pm to yourself " + usage);
                        return false;
                    }
                    String message = stringFromArgs(args, 1);
                    debugPlayerMessage(player,"A PM was sent from " + player.getName() + " to " + target.getName());
                    debugPlayerMessage(player, "MESSAGE CONTENTS &7>> " + message);
                    sendCleanMessage(target,  player.getName() + " &f-> You&7 >>&f " + message);
                    sendCleanMessage(player,  "You&7 &f-> " + target.getName() + " &7>>&f " + message);

                    main.getRecentMessages().put(target.getUniqueId(), player.getUniqueId());
                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());

                } else { // If the player doesn't exist
                    sendMessage(player, "&cPlayer not found. " + usage);
                }
            } else { // If the player typed in the wrong amount of arguments
                sendMessage(player, usage);
            }
        } // THE SENDER IS CONSOLE.
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}