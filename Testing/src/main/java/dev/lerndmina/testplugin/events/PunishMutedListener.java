package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.Main;
import io.papermc.paper.event.player.AsyncChatEvent;
import io.papermc.paper.event.player.ChatEvent;
import org.bukkit.event.EventHandler;

public class PunishMutedListener extends AbstractEvent {

    public PunishMutedListener(Main main) {
        super(main);
    }

    @EventHandler
    public void onChat(AsyncChatEvent e) {
        debugPlayerMessage(e.getPlayer(), "Event: AsyncChatEvent triggered");
        if (main.muted.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            sendMessage(e.getPlayer(), configString("muted-notify"));
        }
    }
}
