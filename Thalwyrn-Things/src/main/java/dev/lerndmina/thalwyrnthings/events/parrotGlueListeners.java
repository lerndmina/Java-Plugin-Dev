package dev.lerndmina.thalwyrnthings.events;

import dev.lerndmina.thalwyrnthings.Main;
import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.function.Consumer;

public class parrotGlueListeners implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onCreatureSpawn(CreatureSpawnEvent event){
        if (event.getEntity().getType() != EntityType.PARROT){
            return;
        }
        Parrot parrot = (Parrot) event.getEntity();
        if (parrot.getOwnerUniqueId() == null){
            return;
        }
        AnimalTamer owner = parrot.getOwner();
        if (owner == null){
            return;
        }
        Player player = (Player) owner;
        if (!(player.isOnline())){
            return;
        }
        Main main = Main.getInstance();
        if (!main.parrotList.contains(player.getUniqueId())){
            return;
        }
        if (player.getShoulderEntityLeft() == null && player.getShoulderEntityRight() == null){
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerSneak(PlayerToggleSneakEvent event){
        if(!event.isSneaking() || event.getPlayer().isFlying() || event.getPlayer().isGliding()){
            return;
        }
        Player player = event.getPlayer();
        Main main = Main.getInstance();
        debugPlayerMsg(player, "You are sneaking on the ground");
        if (!main.parrotList.contains(player.getUniqueId())){
            return;
        }
        debugPlayerMsg(player, "You are sneaking and have parrot glue");
        main.parrotList.remove(player.getUniqueId());
        debugPlayerMsg(player, "Temporaraly removed parrot glue to allow parrot to spawn on the ground");
        Parrot parrot = (Parrot)player.getShoulderEntityLeft();
        if (parrot != null) {
            parrot.spawnAt(player.getLocation());
            player.setShoulderEntityLeft(null);
        }
        parrot = (Parrot)player.getShoulderEntityRight();
        if (parrot != null) {
            parrot.spawnAt(player.getLocation());
            player.setShoulderEntityRight(null);
        }
        main.parrotList.add(player.getUniqueId());
    }
}
