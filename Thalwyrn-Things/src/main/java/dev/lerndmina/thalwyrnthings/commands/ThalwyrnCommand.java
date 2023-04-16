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

import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.playerMsg;

public class ThalwyrnCommand extends Command {

    public ThalwyrnCommand(Main main) {
        super(
                main,
                true,
                "thalwyrn",
                "A test command woah",
                "Put some usage here bro",
                "thalwyrn.commands.CHANGEME",
                new String[]{}
        );
    }

    @Override // Handle command if run by player, Allows perm checks.
    public void executePlayer(Player player, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload") && player.hasPermission("thalwyrn.commands.reload")) {
            main.saveDefaultConfig();
            main.reloadConfig();
            main.debug = main.getConfig().getBoolean("debug");
            for (Player p : main.getServer().getOnlinePlayers()) {
                if (!main.scoreboardDisabledList.contains(p.getUniqueId())) {
                    p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                    ScoreboardListener.getListener().buildScoreboard(p);
                }
            }
            playerMsg(player, "Config reloaded.");
            return;
        }
    }

    @Override // This only runs if requirePlayer is false
    public void executeConsole(CommandSender console, String[] args) {}


    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        if(args.length == 1){
            return Collections.singletonList("reload");
        }
        return Collections.emptyList();
    }
}
