package dev.lerndmina.thalwyrnthings.commands;

import dev.lerndmina.thalwyrnthings.Main;
import dev.lerndmina.thalwyrnthings.Utils.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.*;

public class parrotGlue extends Command {

    public parrotGlue(Main main) {
        super(
                main,
                true,
                "parrotglue",
                "A test command woah",
                "Put some usage here bro",
                "thalwyrn.commands.parrotglue",
                new String[]{}
        );
    }

    @Override // Handle command if run by player, Allows perm checks.
    public void executePlayer(Player player, String[] args) {
        Entity shoulderEntityLeft = player.getShoulderEntityLeft();
        Entity shoulderEntityRight = player.getShoulderEntityRight();
        if (shoulderEntityLeft == null && shoulderEntityRight == null){
            playerMsg(player, "You have no parrots on your shoulders!");
            return;
        }
        Parrot parrotLeft = null;
        Parrot parrotRight = null;

        if (shoulderEntityLeft instanceof Parrot){
            parrotLeft = (Parrot) shoulderEntityLeft;
            if (isNotOwner(parrotLeft, player)){
                playerMsg(player, "The left parrot is not yours.");
            }
        }
        if (shoulderEntityRight instanceof Parrot){
            parrotRight = (Parrot) shoulderEntityRight;
            if (isNotOwner(parrotRight, player)){
                playerMsg(player, "The right parrot is not yours.");
            }
        }
        UUID uuid = player.getUniqueId();

        if (main.parrotList.contains(uuid)){
            main.parrotList.remove(uuid);
            playerMsg(player, "Parrots are now free to leave.");
        } else {
            main.parrotList.add(uuid);
            playerMsg(player,"You attached some really strong parrot glue.");
        }



    }

    boolean isNotOwner(Parrot parrot, Player player){
        return parrot.getOwner().getUniqueId() != player.getUniqueId();
    }

    @Override // This only runs if requirePlayer is false
    public void executeConsole(CommandSender console, String[] args) {}


    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
