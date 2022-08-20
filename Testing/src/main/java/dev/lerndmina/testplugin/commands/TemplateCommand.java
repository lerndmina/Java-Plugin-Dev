package dev.lerndmina.testplugin.commands;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TemplateCommand extends AbstractCommand {

    public TemplateCommand(Main main) { // Import main for use in this class
        super(main);
    }

    int coolDownTime = 5000; // 5 seconds

    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(coolDownTime, TimeUnit.MILLISECONDS).build();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = isPlayerAndHasPermission(sender, "wild.command.REPLACEME", false);
        MessageHandler msg = new MessageHandler();
        if (p != null) {
            // Command code here
            msg.player(p, "Uh, I don't know what you're trying to do. I think it's working...");
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList(
                    "0 Here",
                    "1 is",
                    "2 a",
                    "3 list",
                    "4 of",
                    "5 arguments",
                    "6 for",
                    "7 array",
                    "8 pos",
                    "9 1"
            ), new ArrayList<>());
        }
        return null;
    }
}