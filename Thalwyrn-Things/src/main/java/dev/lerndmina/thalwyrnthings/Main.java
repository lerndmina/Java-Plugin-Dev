package dev.lerndmina.thalwyrnthings;
import dev.lerndmina.thalwyrnthings.Utils.LogFilter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        // Set the default config ;)
        saveDefaultConfig();

        LogFilter filter = new LogFilter(this);
        filter.registerFilter();




    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean debug = false;
    public String baseCommand = "thalwyrn";

    public enum consoleTypes{
        INFO, WARN, SEVERE
    }


}
