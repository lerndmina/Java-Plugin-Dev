package dev.lerndmina.thalwyrnthings.commands;

import dev.lerndmina.thalwyrnthings.Main;
import dev.lerndmina.thalwyrnthings.Utils.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.playerMsg;

public class SlapCommand extends Command {

    public SlapCommand(Main main) {
        super(
                main,
                true,
                "slap",
                "Slap someone, or yourself",
                "/slap [player]",
                "thalwyrn.commands.slap",
                new String[]{}
        );
    }

    @Override // Handle command if run by player, Allows perm checks.
    public void executePlayer(Player player, String[] args) {
        if (args.length != 1) {
            playerMsg(player, this.usageMessage);
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            playerMsg(player, "Player not found.");
            return;
        }
        target.damage(2);
        playerMsg(player, "You slapped " + target.getName());
        playerMsg(target, player.getName() + " slapped you!");
    }

    @Override // This only runs if requirePlayer is false
    public void executeConsole(CommandSender console, String[] args) {
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            List<String> names = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                names.add(player.getName());
            }
            return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());
        }
        return Collections.emptyList();
    }
}
