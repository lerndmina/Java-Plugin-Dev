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
        if (!Main.getInstance().currentlyGlideBoosting.contains(event.getPlayer().getUniqueId())) {
            return;
        }
        Player p = event.getPlayer();
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
}
