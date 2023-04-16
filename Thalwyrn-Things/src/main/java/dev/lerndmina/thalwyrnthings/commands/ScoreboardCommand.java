package dev.lerndmina.thalwyrnthings.commands;

import dev.lerndmina.thalwyrnthings.Main;
import dev.lerndmina.thalwyrnthings.Utils.Command;
import dev.lerndmina.thalwyrnthings.events.ScoreboardListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.*;

public class ScoreboardCommand extends Command {

    public ScoreboardCommand(Main main) {
        super(
                main,
                true,
                "scoreboard",
                "Toggle your scoreboard",
                "/scoreboard [on/off]",
                "thalwyrn.commands.scoreboard",
                new String[]{}
        );
    }


    @Override // Handle command if run by player, Allows perm checks.
    public void executePlayer(Player player, String[] args) {
        if (args.length == 0){
            if(main.scoreboardDisabledList.contains(player.getUniqueId())){
                main.scoreboardDisabledList.remove(player.getUniqueId());
                playerMsg(player, "Scoreboard enabled.");
            } else {
                main.scoreboardDisabledList.add(player.getUniqueId());
                playerMsg(player, "Scoreboard disabled.");
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("on")){
            main.scoreboardDisabledList.remove(player.getUniqueId());
            playerMsg(player, "Scoreboard enabled.");
        } else if (args.length == 1 && args[0].equalsIgnoreCase("off")) {
            main.scoreboardDisabledList.add(player.getUniqueId());
            playerMsg(player, "Scoreboard disabled.");
        } else {
            playerMsg(player, this.usageMessage);
            return;
        }
        ScoreboardListener.getListener().buildScoreboard(player);
    }

    @Override // This only runs if requirePlayer is false
    public void executeConsole(CommandSender console, String[] args) {}


    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
         if (args.length == 1){
            return Arrays.asList("on", "off");
        }
        return Collections.emptyList();
    }
}
