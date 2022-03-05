package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.AbstractHelper;
import dev.lerndmina.testplugin.Main;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkListener extends AbstractEvent implements Listener {
    public FireworkListener(Main main) {
        super(main); // Import main for use in this class
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){ // Launch a firework on move if sneaking & toggled

        if (main.fireworkEnabled){
            if (e.getPlayer().isSneaking()){ // Only fire once as event triggers on both sneak and unsneak
                Firework firework = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
                FireworkMeta meta = firework.getFireworkMeta();
                meta.addEffect(FireworkEffect.builder().withColor(Color.FUCHSIA).withColor(Color.AQUA).with(FireworkEffect.Type.BALL).withFlicker().build());
                meta.setPower(1); // Power 1-3
                firework.setFireworkMeta(meta);
            }
        }
    }


    //Cancel damage if done by a firework
    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if ((e.getEntity() instanceof Player) && (e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION))){
            e.setCancelled(true);
        }
    }

}
