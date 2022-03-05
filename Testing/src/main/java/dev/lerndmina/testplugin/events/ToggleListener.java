package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.AbstractHelper;
import dev.lerndmina.testplugin.Main;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToggleListener extends AbstractEvent{

    private final List<UUID> chatDisabled = new ArrayList<>();

    public ToggleListener(Main main) {
        super(main); // Import main for use in this class
        // Define main class
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR) && (e.getHand().equals(EquipmentSlot.HAND))) { // If the player is holding a nether star
            if (hasPermission(player, "toggle.chat")) { // After item is found check permissions


                if (chatDisabled.contains(player.getUniqueId())) { // If the player is in the list of disabled players
                    chatDisabled.remove(player.getUniqueId());
                    sendMessage(player, configString("chat-enabled"));

                } else { // If the player is not in the list of disabled players
                    chatDisabled.add(player.getUniqueId());
                    sendMessage(player, configString("chat-disabled"));
                }
            }
        }
    }

    @EventHandler
    public void onChat(AsyncChatEvent e) {
        if (chatDisabled.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            sendMessage(e.getPlayer(), "chat-disabled-notify");
        }
    }
}
