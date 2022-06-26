package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VanishCommand extends AbstractCommand {

    public VanishCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = isPlayerAndHasPermission(sender, "wild.command.vanish", false); // Check if player is online and has permission
        if (player != null) {

            if (isLNotListed(player)) { // They are not vanished, vanish them
                add(player);
                for (Player target : main.getServer().getOnlinePlayers()){
                    target.hidePlayer(main, player);
                }
                sendMessage(player, configString("vanish-enabled"));

            } else { // They are vanished un-vanish them
                remove(player);
                for (Player target : main.getServer().getOnlinePlayers()){
                    target.showPlayer(main, player);
                }
                sendMessage(player, configString("vanish-disabled"));
            }
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
