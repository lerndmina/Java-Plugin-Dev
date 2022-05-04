package dev.lerndmina.testplugin.events;


import dev.lerndmina.testplugin.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class JoinLeaveListener extends AbstractEvent{


    public JoinLeaveListener(Main main) {
        super(main);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        e.getPlayer().sendTitle(
                (parseColor("Welcome &e" + e.getPlayer().getName())),
                parseColor("&6Hello There"),
                20,
                20,
                20);

    }
}