package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlyCommand extends AbstractCommand {

    public FlyCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = isPlayerAndHasPermission(sender, "wild.command.fly", false); // Check if player is online and has permission
        if (player != null) { // Execute the command and run this code.
            if (args.length == 0){
                if (player.getAllowFlight()){
                    player.setAllowFlight(false);
                    sendMessage(player,"You toggled off flight.");
                } else {
                    player.setAllowFlight(true);
                    sendMessage(player,"You toggled on flight.");
                }

            } else if (args.length == 1){
                if (Bukkit.getPlayer(args[0]) != null){
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target.getAllowFlight()){
                        target.setAllowFlight(false);
                        sendMessage(target,player.getName() + " disabled flight for you!");

                    } else {
                        target.setAllowFlight(true);
                        sendMessage(target,player.getName() + " enabled flight for you!");
                    }
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}