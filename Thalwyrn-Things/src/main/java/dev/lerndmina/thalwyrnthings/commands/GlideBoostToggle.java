package dev.lerndmina.thalwyrnthings.commands;

import dev.lerndmina.thalwyrnthings.Main;
import dev.lerndmina.thalwyrnthings.Utils.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.playerMsg;

public class GlideBoostToggle extends Command {

    public GlideBoostToggle(Main main) {
        super(
                main,
                true,
                "glideboost",
                "Enable/disable elytra boost",
                "/glideboost",
                "thalwyrn.commands.glideboost",
                new String[]{}
        );
    }

    @Override // Handle command if run by player, Allows perm checks.
    public void executePlayer(Player player, String[] args) {
        UUID uuid = player.getUniqueId();
        if (main.glideBoostList.contains(uuid)){
            main.glideBoostList.remove(uuid);
            playerMsg(player, "Elytra boost disabled.");
            return;
        }
        main.glideBoostList.add(uuid);
        playerMsg(player, "Elytra boost enabled.");
    }

    @Override // This only runs if requirePlayer is false
    public void executeConsole(CommandSender console, String[] args) {}


    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
