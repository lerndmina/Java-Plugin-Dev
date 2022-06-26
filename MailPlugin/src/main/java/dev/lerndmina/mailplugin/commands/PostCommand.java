package dev.lerndmina.mailplugin.commands;

import dev.lerndmina.mailplugin.MailPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PostCommand extends AbstractCommand{
    public PostCommand(MailPlugin main) {
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = isPlayerAndHasPermission(sender, "MailPlugin.command.CHANGEME", true);
        if (p != null){
            sendMessage(p, stringFromArgs(args, 0));
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //TODO: Implement tab completion

        return null;
    }
}
