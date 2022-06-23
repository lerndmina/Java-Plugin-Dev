package dev.lerndmina.testplugin.commands.privateMessage;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.commands.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ReplyCommand extends AbstractCommand {

    public ReplyCommand(Main main) { // Import main for use in this class
        super(main);
    }

    //TODO: /r <message>
    String usage = "&e/r <message>";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = isPlayerAndHasPermission(sender, "wild.command.reply", false); // Check if player is online and has permission
        if (player != null) { // Execute the command and run this code.
            if (args.length == 0){
                sendMessage(player, usage);
                debugPlayerMessage(player, "Not enough arguments");
                return false;
            }
            if(main.getRecentMessages().containsKey(player.getUniqueId())){
                UUID uuid = main.getRecentMessages().get(player.getUniqueId());
                Player target = Bukkit.getPlayer(uuid);
                if (target == null){
                    sendMessage(player, "The person you are trying to message is either not online or does not exist.");
                    return false;
                }
                String message = stringFromArgs(args, 0);
                sendCleanMessage(target,  player.getName() + " &f-> You&7 >>&f " + message);
                sendCleanMessage(player,  "You&7 &f-> " + target.getName() + " &7>>&f " + message);

            } else{
                sendMessage(player, "You have nobody to message :( Strike up a conversation with someone!");
                debugPlayerMessage(player, "UUID not found in hashmap");
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}