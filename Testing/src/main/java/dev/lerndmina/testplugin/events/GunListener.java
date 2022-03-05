package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.AbstractHelper;
import dev.lerndmina.testplugin.Main;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GunListener extends AbstractEvent{

    // Read values from config.
    Material snowballgun = Material.matchMaterial(configString("guns.snowballgun.item"));
    Material arrowgun = Material.matchMaterial(configString("guns.arrowgun.item"));

    public GunListener(Main main) {
        super(main); // Import main for use in this class
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        debugPlayerMessage(player, "Event triggered");


        if (main.isGunEnabled(player)) { // Player is allowed to use guns.
            debugPlayerMessage(player, "Guns are enabled");
            if (hasItemInHand(player, snowballgun)) {
                player.launchProjectile(Snowball.class);
                debugPlayerMessage(player, configString("guns.snowballgun.message"));
            } else if (hasItemInHand(player, arrowgun)) {
                player.launchProjectile(Arrow.class);
                debugPlayerMessage(player, configString("guns.arrowgun.message"));
            }
        }
    }

    @EventHandler
    public void onArrowLand(ProjectileHitEvent e) {
        Projectile projectile = e.getEntity();

        if (projectile.getShooter() instanceof Player) {
            Player player = (Player) projectile.getShooter();

            if ((main.isGunEnabled(player) && (projectile instanceof Arrow) && (hasItemInHand(player, arrowgun)))) {
                projectile.remove();
                debugPlayerMessage(player, "Projectile removed");
            }
        }
    }
}
