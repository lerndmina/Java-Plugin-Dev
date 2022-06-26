package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FlyBoostListener extends AbstractEvent {

    private final List<UUID> chatDisabled = new ArrayList<>();

    public FlyBoostListener(Main main) {
        super(main); // Import main for use in this class
        // Define main class
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (p.isGliding() && (p.isSneaking() && (main.flightEnabled.contains(p.getUniqueId())))) {

            // increase player forward velocity based on looking direction
            p.setVelocity(p.getLocation().getDirection().multiply(2));

        }

    }
}