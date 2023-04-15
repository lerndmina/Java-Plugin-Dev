package dev.lerndmina.thalwyrnthings.events;

import dev.lerndmina.thalwyrnthings.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.*;

public class ElytraBoostListener implements Listener {
    String permission = "thalwyrn.elytraboost";

    @EventHandler(ignoreCancelled = true)
    public void onSneakWhileGliding(PlayerToggleSneakEvent event){

        // check if player is first in gliding list and then if they are sneaking
        if (!Main.getInstance().glideBoostList.contains(event.getPlayer().getUniqueId())) {
            return;
        }
        Player p = event.getPlayer();
        debugPlayerMsg(p, "You are sneaking");
        if (p.hasPermission(permission) && p.isGliding() && p.isSneaking()){
            if (Main.getInstance().currentlyGlideBoosting.contains(p.getUniqueId())){
                Main.getInstance().currentlyGlideBoosting.remove(p.getUniqueId());
                debugPlayerMsg(p, "You are no longer boosting");
            } else {
                Main.getInstance().currentlyGlideBoosting.add(p.getUniqueId());
                debugPlayerMsg(p, "You are now boosting");
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    private void onMoveWhileGliding(PlayerMoveEvent event){
        // If player is not in the list we return as quickly as possible to save resources
        if (!Main.getInstance().currentlyGlideBoosting.contains(event.getPlayer().getUniqueId())) {
            return;
        }
        Player p = event.getPlayer();
        // If player is not gliding we remove them from the currently boosting list to prevent the boost activating next time they fly.
        if (!p.isGliding()){
            Main.getInstance().currentlyGlideBoosting.remove(p.getUniqueId());
            return;
        }
        if (p.hasPermission(permission) && p.isGliding()){
            p.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(2));
        }
    }

    @EventHandler(ignoreCancelled = true)
    private void onEntityDamage(EntityDamageEvent event)
    {
        if(!(event.getEntity() instanceof Player))
        {
            return;
        }
        Player p = (Player) event.getEntity();
        // Cancel entity damage while gliding
        if(p.isGliding() && p.hasPermission(permission))
        {
            event.setCancelled(true);
        }
    }

    // Clean up when player leaves server
    @EventHandler(ignoreCancelled = true)
    private void onPlayerLeave(PlayerQuitEvent event){
        // .remove only removes if the object is present, so no need to check if it is in the list.
        Main.getInstance().currentlyGlideBoosting.remove(event.getPlayer().getUniqueId());
    }
}
