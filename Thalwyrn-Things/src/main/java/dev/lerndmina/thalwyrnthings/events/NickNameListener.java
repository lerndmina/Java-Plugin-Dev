package dev.lerndmina.thalwyrnthings.events;

import dev.lerndmina.thalwyrnthings.Main;
import dev.lerndmina.thalwyrnthings.Utils.StringHelpers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.parseComponent;

public class NickNameListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    private void onJoin(PlayerJoinEvent event){
        StringHelpers.debugConsoleMsg("Player joined");
        StringHelpers.debugConsoleMsg(Main.getInstance().nicknames.toString());
        UUID uuid = event.getPlayer().getUniqueId();
        if(Main.getInstance().nicknames.containsKey(uuid)){
            Player player = event.getPlayer();
            event.getPlayer().displayName(parseComponent(Main.getInstance().nicknames.get(uuid), player));
            event.getPlayer().playerListName(parseComponent(Main.getInstance().nicknames.get(uuid), player));
        }
    }
}
