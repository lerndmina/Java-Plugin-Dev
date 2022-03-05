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

public class GunListener extends AbstractEvent implements Listener {
    private final Main main; // Define main class
    public GunListener(Main main) {
        super(main); // Import main for use in this class
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        // Read values from config.
        Material snowballgun = Material.matchMaterial(configString("guns.snowballgun.item"));
        Material arrowgun = Material.matchMaterial(configString("guns.arrowgun.item"));


        if (main.gunsEnabled.contains(player.getUniqueId())) { // Player is allowed to use guns.
            if (hasItemInHand(player, snowballgun)) {
                player.launchProjectile(Snowball.class);
                sendMessage(player, configString("guns.snowballgun.message"));
            } else if (hasItemInHand(player, arrowgun)) {
                player.launchProjectile(Arrow.class);
                sendMessage(player, configString("guns.arrowgun.message"));
            }
        }
    }

    @EventHandler
    public void onArrowLand(ProjectileHitEvent e) {
        Projectile projectile = e.getEntity();

        if (projectile.getShooter() instanceof Player) {
         Player p = (Player) projectile.getShooter();
            if ((main.gunsEnabled.contains(p.getUniqueId()) && (projectile instanceof Arrow))) {
                projectile.remove();
                debugPlayerMessage(p, "Projectile removed");
            }
        }
    }
}
