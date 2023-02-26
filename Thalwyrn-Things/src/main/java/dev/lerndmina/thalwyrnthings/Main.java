package dev.lerndmina.thalwyrnthings;
import dev.lerndmina.thalwyrnthings.commands.TemplateCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        // Set the default config ;)
        saveDefaultConfig();

        new TemplateCommand(this);




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
