package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class MenuListener extends AbstractEvent {
    public MenuListener(Main main) {
        super(main); // Import main for use in this class
        // Define main class
    }

    @EventHandler
    private void onClick(InventoryClickEvent e){

        Player player = (Player) e.getWhoClicked();

        debugPlayerMessage(player, "Event triggered");

        if ((ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(parseColor("&6&lMain Menu"))) // !!! This is so dumb !!!
        && (e.getCurrentItem() != null)){
            debugPlayerMessage(player, "Passed all checks");

            e.setCancelled(true);
            switch (e.getRawSlot()){
                case 0:
                    break;
                case 11: // Flytoggle
                    if (player.getAllowFlight()){
                        player.setAllowFlight(false);
                        sendMessage(player, "You turned off flight");
                    } else {
                        player.setAllowFlight(true);
                        sendMessage(player, "You turned on flight");
                    }
                    break;
                case 15: // Randomtp to player
                    Random random = new Random();
                    Player target = ((Player) Bukkit.getOnlinePlayers().toArray()[random.nextInt(Bukkit.getOnlinePlayers().size())]);
                    player.teleport(target);
                    sendMessage(target, player.getName() + " teleported to you randomly!");
                    sendMessage(player, "You teleported to a random person");
                    break;
                default:
                    return;
            }

            player.closeInventory();
        }
    }


}