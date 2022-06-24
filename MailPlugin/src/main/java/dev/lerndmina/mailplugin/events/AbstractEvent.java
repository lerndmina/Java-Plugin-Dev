package dev.lerndmina.mailplugin.events;

import dev.lerndmina.mailplugin.AbstractHelper;
import dev.lerndmina.mailplugin.MailPlugin;
import org.bukkit.event.Listener;

public abstract class AbstractEvent extends AbstractHelper implements Listener {
    public AbstractEvent(MailPlugin main) {
        super(main);
    }



}
