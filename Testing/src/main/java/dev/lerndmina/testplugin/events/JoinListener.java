package dev.lerndmina.testplugin.events;


import dev.lerndmina.testplugin.AbstractHelper;
import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.commands.AbstractCommand;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinListener extends AbstractEvent{


    public JoinListener(Main main) {
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