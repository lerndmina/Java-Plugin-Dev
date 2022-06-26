package dev.lerndmina.mailplugin;

import dev.lerndmina.mailplugin.commands.TemplateCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

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

    HashMap<UUID, LinkedHashMap<String, String>> mail = new HashMap<>();

    public void setMail(UUID sender, String recipient, String message) {
        if (!mail.containsKey(sender)) {
            mail.put(sender, new LinkedHashMap<>());
        }
        mail.get(sender).put(recipient, message);
    }

    public String getMail(UUID sender, String recipient) {
        if (!mail.containsKey(sender)) {
            return null;
        }
        if (!mail.get(sender).containsKey(recipient)) {
            return null;
        }
        return mail.get(sender).get(recipient);
    }
    public boolean deleteMail(UUID sender, String recipient) {
        if (!mail.containsKey(sender)) {
            return false;
        }
        if (!mail.get(sender).containsKey(recipient)) {
            return false;
        }
        mail.get(sender).remove(recipient);
        return true;
    }
}
