package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TemplateCommand extends AbstractCommand {

    public TemplateCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = isPlayerAndHasPermission(sender, "wild.command.REPLACEME", false);
        if (player != null) { // Execute the command and run this code.
            sendMessage(player, stringFromArgs(args, 1));
        }
        return false;
    }
}