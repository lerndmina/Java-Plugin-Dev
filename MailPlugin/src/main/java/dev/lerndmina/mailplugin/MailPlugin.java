package dev.lerndmina.mailplugin;

import dev.lerndmina.mailplugin.commands.TemplateCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class MailPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info(getConfig().getString("plugin-loaded"));
        getCommand("template").setExecutor(new TemplateCommand(this));


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean debug = false;
}
