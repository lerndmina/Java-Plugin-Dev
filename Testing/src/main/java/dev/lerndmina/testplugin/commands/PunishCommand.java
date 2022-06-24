package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class PunishCommand extends AbstractCommand {

    public PunishCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = isPlayerAndHasPermission(sender, "wild.command.REPLACEME", false); // Check if player is online and has permission
        String usage = " &cUse: &n/punish <player> <kick/ban/tempban/unban/mute/unmute> <time> [reason]";

        if (player != null) { // Execute the command and run this code.
            if (args.length >= 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) { // Handle online players.
                    // Check if reason exists if not say no reason
                    String reason = null;
                    Integer getTime = null;
                    String fullReason = null;
                    try {
                        getTime = Integer.parseInt(args[2]);
                    } catch (Exception ignored) {
                    }

                    if (getTime != null) {
                        reason = stringFromArgs(args, 3);
                    } else {
                        reason = stringFromArgs(args, 2);
                    }

                    debugPlayerMessage(player, "\"" + reason + "\"");

                    if (reason.equals("")) {
                        reason = "No reason provided";
                    }

                    switch (args[1].toLowerCase()) {
                        case "kick":
                            target.kickPlayer("You have been kicked for " + reason + "\n This action was performed by " + player.getName());
                            break;

                        case "ban":
                            fullReason = "You have been banned for " + reason + "\n This action was performed by " + player.getName();
                            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), fullReason, null, null);
                            target.kickPlayer(fullReason);
                            break;

                        case "tempban":
                            fullReason = "You have been tempbanned for " + reason + "\n This action was performed by " + player.getName();
                            try {
                                getTime = Integer.parseInt(args[2]);
                            } catch (Exception e) {
                                sendMessage(player, "Time must be a number in hours" + usage);
                                break;
                            }
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.HOUR, getTime);
                            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), fullReason, cal.getTime(), null);
                            target.kickPlayer(fullReason);
                            break;

                        case "mute":
                            fullReason = "You have been muted for " + reason + " by " + player.getName();
                            if (main.muted.contains(target.getUniqueId())) {
                                sendMessage(player, target.getName() + " is already muted");
                                break;
                            } else {
                                main.muted.add(target.getUniqueId());
                                main.saveMutes();
                                sendMessage(player,  target.getName() + " has been muted");
                                sendMessage(target, fullReason);
                                break;
                            }
                        case "unmute":
                            fullReason = "You have been unmuted by " + player.getName();
                            if (!main.muted.contains(target.getUniqueId())) {
                                sendMessage(player, target.getName() + " is not muted");
                                break;
                            } else {
                                main.muted.remove(target.getUniqueId());
                                main.saveMutes();
                                sendMessage(player,  target.getName() + " has been unmuted");
                                sendMessage(target, fullReason);
                                break;
                            }
                        default:
                            sendMessage(player, "&cIncorrect command argument. Please specify kick/ban/tempban/mute/unmute");
                            return false;
                    }
                } else { // Player does not exist
                    sendMessage(player, "&cThe player you are trying to punish does not exist, or has never joined the server.");
                }
            } else {
                sendMessage(player, "&cInvalid command usage." + usage);
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> names = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                names.add(player.getName());
            }
            return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());
        } else if (args.length == 2){
            return StringUtil.copyPartialMatches(args[1], Arrays.asList(
                    "kick",
                    "ban",
                    "tempban",
                    "mute",
                    "unmute"
            ), new ArrayList<>());
        } else if (args.length == 3){
            return Collections.singletonList("time / reason");
        } else if (args.length == 4){
            return Collections.singletonList("reason");
        }
        return null;
    }
}