package dev.lerndmina.thalwyrnthings.commands;

import dev.lerndmina.thalwyrnthings.Main;
import dev.lerndmina.thalwyrnthings.Utils.Command;
import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.*;

import dev.lerndmina.thalwyrnthings.Utils.StringHelpers;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.playerMsg;

public class NickNameCommand extends Command {

    public NickNameCommand(Main main) {
        super(
                main,
                true,
                "nick",
                "Set your nickname",
                "/nick [user] {nickname}",
                "thalwyrn.commands.nick",
                new String[]{}
        );
    }

    @Override // Handle command if run by player, Allows perm checks.
    public void executePlayer(Player player, String[] args) {
        if (args.length != 1) {
            playerMsg(player, "Incorrect usage. " + this.usageMessage);
            return;
        }
        player.displayName(parseComponent(args[0], player));
        player.playerListName(parseComponent(args[0], player));
        playerMsg(player, "Your nickname has been set to " + args[0]);
        main.nicknames.put(player.getUniqueId(), parseString(args[0]));
    }

    @Override // This only runs if requirePlayer is false
    public void executeConsole(CommandSender console, String[] args) {}


    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
