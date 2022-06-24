package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlyBoostCommand extends AbstractCommand {

    public FlyBoostCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = isPlayerAndHasPermission(sender, "wild.command.flyboost", false);
        String usage = "Usage >> /flyboost [on|true|enable|toggle|off|false|disable]";
        if (p != null) { // Execute the command and run this code.
            if (args.length == 0 || (args.length == 1 && listCompare(args[0],"toggle"))){
                if (main.flightEnabled.contains(p.getUniqueId())){
                    main.flightEnabled.remove(p.getUniqueId());
                    sendMessage(p, "FlyBoost disabled");
                } else {
                    main.flightEnabled.add(p.getUniqueId());
                    sendMessage(p, "FlyBoost enabled");
                }
            } else if (args.length == 1){
                if (listCompare(args[0], "on", "true", "enable")){
                    main.flightEnabled.add(p.getUniqueId());
                    sendMessage(p, "FlyBoost enabled");
                } else if (listCompare(args[0], "off", "false", "disable")){
                    main.flightEnabled.remove(p.getUniqueId());
                    sendMessage(p, "FlyBoost disabled");
                } else {
                    sendMessage(p, usage);
                }
            } else {
                sendMessage(p, usage);
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> results = new ArrayList<>();
        if (args.length == 1){
            return StringUtil.copyPartialMatches(args[0], Arrays.asList(
                    "true",
                    "false",
                    "toggle",
                    "on",
                    "off",
                    "enable",
                    "disable"
            ), new ArrayList<>());
        }
        return results;
    }
}

