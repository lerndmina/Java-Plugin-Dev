package dev.lerndmina.thalwyrnthings.events;

import dev.lerndmina.thalwyrnthings.Main;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.function.Consumer;

public class parrotGlueListeners implements Listener {

    @EventHandler
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

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event){
        if(!event.isSneaking()){
            return;
        }
        Player player = event.getPlayer();
        Main main = Main.getInstance();
        if (!main.parrotList.contains(player.getUniqueId())){
            return;
        }
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
    }
}
