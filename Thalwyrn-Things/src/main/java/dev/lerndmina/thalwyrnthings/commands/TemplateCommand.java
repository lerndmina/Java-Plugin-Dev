package dev.lerndmina.thalwyrnthings.commands;

import dev.lerndmina.thalwyrnthings.Main;
import dev.lerndmina.thalwyrnthings.Utils.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;

public class TemplateCommand extends Command {

    public TemplateCommand(Main main) {
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
        playerMsg(player,"This command worlked!");
        playerMsg(player,this.getUsage());
    }

    @Override // This only runs if requirePlayer is false
    public void executeConsole(CommandSender console, String[] args) {}


    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
