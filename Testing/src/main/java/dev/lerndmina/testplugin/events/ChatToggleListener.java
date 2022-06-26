package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractEvent;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ChatToggleListener extends AbstractEvent {


    public ChatToggleListener(Main main) {
        super(main); // Import main for use in this class
        // Define main class
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR) && (e.getHand().equals(EquipmentSlot.HAND))) { // If the player is holding a nether star
            if (hasPermission(player, "toggle.chat")) { // After item is found check permissions


                if (main.chatDisabled.contains(player.getUniqueId())) { // If the player is in the list of disabled players
                    main.chatDisabled.remove(player.getUniqueId());
                    sendMessage(player, configString("chat-enabled"));

                } else { // If the player is not in the list of disabled players
                    main.chatDisabled.add(player.getUniqueId());
                    sendMessage(player, configString("chat-disabled"));
                }
            }
        }
    }

    @EventHandler
    public void onChat(AsyncChatEvent e) {
        if (main.chatDisabled.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            sendMessage(e.getPlayer(), "chat-disabled-notify");
        }
    }
}
