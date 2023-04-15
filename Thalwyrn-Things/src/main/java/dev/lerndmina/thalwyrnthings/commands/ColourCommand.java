package dev.lerndmina.thalwyrnthings.commands;

import dev.lerndmina.thalwyrnthings.Main;
import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.*;
import dev.lerndmina.thalwyrnthings.Utils.Command;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.playerMsg;

public class ColourCommand extends Command {

    public ColourCommand(Main main) {
        super(
                main,
                true,
                "colour",
                "A test command woah",
                "/colour",
                "thalwyrn.commands.colour",
                new String[]{}
        );
    }

    @Override // Handle command if run by player, Allows perm checks.
    public void executePlayer(Player player, String[] args) {
        String string = stringFromArgs(args, 0);
        string = parseString(string);
        System.out.println(string);
        player.sendMessage(string);
    }

    @Override // This only runs if requirePlayer is false
    public void executeConsole(CommandSender console, String[] args) {}


    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
