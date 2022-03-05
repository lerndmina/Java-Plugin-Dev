package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.AbstractHelper;
import dev.lerndmina.testplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractCommand extends AbstractHelper implements CommandExecutor {
    private final Main main; // Define main class
    private final List<UUID> list = new ArrayList<>(); // Define vanished list

    public AbstractCommand(Main main) {
        super(main); // Import main for use in this class
        this.main = main;
    }

    @Override
    public abstract boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);

    public boolean isLNotListed(Player player) {
        return (!isListed(player));
    }

    public boolean isListed(Player player) {
        return (list.contains(player.getUniqueId()));
    }

    public void add(Player player) {
        if (this.isListed(player)) {
            list.add(player.getUniqueId());
        }
    }

    public void remove(Player player) {
        list.remove(player.getUniqueId());
    }


    // Check for a player
    public Player isPlayerAndHasPermission(Object sender, String permission) {
        if (sender instanceof Player) { // If sender is player check for permission
            if (permission.equals("") || (hasPermission((Player) sender, permission))) {// If there is no permission return the player
                return (Player) sender;
            } else {
                return null;
            }
        } else { // If sender is console return null
            sendConsoleWarn("&cThis command can only be ran by a player.");
            return null;
        }
    }
}
