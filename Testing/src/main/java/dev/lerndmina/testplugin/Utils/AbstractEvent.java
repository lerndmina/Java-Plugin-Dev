package dev.lerndmina.testplugin.Utils;

import dev.lerndmina.testplugin.Utils.AbstractHelper;
import dev.lerndmina.testplugin.Main;
import org.bukkit.event.Listener;

public abstract class AbstractEvent extends AbstractHelper implements Listener {
    public AbstractEvent(Main main) {
        super(main);
    }



}
