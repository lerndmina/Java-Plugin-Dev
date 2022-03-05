package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.AbstractHelper;
import dev.lerndmina.testplugin.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ElytraFallListener extends AbstractEvent implements Listener{
    public ElytraFallListener(Main main) {
        super(main); // Import main for use in this class
        // Define main class
    }

    @EventHandler
    private void onElytraFall(EntityDamageEvent e) {
        if ((e.getEntity() instanceof Player) && (e.getCause() == DamageCause.FALL)) {
            Player p = (Player) e.getEntity();
            if (!hasPermission(p, "wild.event.elytrafall")) {
                return;
            }
            if (p.getAllowFlight()) {
                debugPlayerMessage(p, "Player can fly returning");
                return;
            }
            ItemStack chest = p.getInventory().getChestplate();
            if (chest == null) {
                debugPlayerMessage(p, "Player has no chestplate returning");
                return;
            }
            if (chest.getType() != Material.ELYTRA) {
                debugPlayerMessage(p, "Player chestplate is not an elytra returning");
                return;
            }
            ItemMeta chestMeta = chest.getItemMeta();
            if (chestMeta == null) {
                debugPlayerMessage(p, "Player chestplate has no meta returning");
                return;
            }
            if (!chestMeta.hasLore()) {
                debugPlayerMessage(p, "Player chestplate has no lore returning");
                return;
            }
            // If the player has an elytra and has some  lore, cancel the fall damage
            debugPlayerMessage(p, "All checks passed, cancelling fall damage");
            e.setCancelled(true);
        }
    }
}
