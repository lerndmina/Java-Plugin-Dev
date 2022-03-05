package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.AbstractHelper;
import dev.lerndmina.testplugin.Main;
import org.bukkit.event.Listener;

public abstract class AbstractEvent extends AbstractHelper implements Listener {
    public AbstractEvent(Main main) {
        super(main);
    }



}
