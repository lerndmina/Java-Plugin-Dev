package dev.lerndmina.testplugin.commands;

import com.google.common.collect.Lists;
import dev.lerndmina.testplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LogCommand extends AbstractCommand {

    public LogCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = isPlayerAndHasPermission(sender, "wild.command.log", false);
        if (p != null) { // Execute the command and run this code.
            if (args.length == 0){
                sendCleanMessage(p, "&7-----------------------------------------------------");
                sendCleanMessage(p, "&7Server command log (&c" + main.cmdLog.size() + "&7):");
                sendCleanMessage(p, "&7-----------------------------------------------------");
                for (String entry : Lists.reverse(main.cmdLog)) {
                    sendCleanMessage(p,entry);
                }
                sendCleanMessage(p, "&7-----------------------------------------------------");
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (args[0].equals("clear")) {
                    main.cmdLog.clear();
                    sendMessage(p,"Log cleared.");
                } else if (target != null) {
                    sendCleanMessage(p, "&7" + target.getName() + "'s command log:");
                    sendCleanMessage(p,"&7-----------------------------------------------------");
                    for (String entry : Lists.reverse(main.cmdLog)) {
                        if (entry.contains(target.getName())) {
                            sendCleanMessage(p,entry);
                        }
                    }
                    sendCleanMessage(p,"&7-----------------------------------------------------");
                } else {
                    sendCleanMessage(p,"Player not found.");
                }

                }

            }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return null;
    }
}