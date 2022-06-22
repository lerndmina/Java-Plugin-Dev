package dev.lerndmina.testplugin;

import dev.lerndmina.testplugin.commands.*;
import dev.lerndmina.testplugin.commands.privateMessage.PrivateMessageCommand;
import dev.lerndmina.testplugin.commands.privateMessage.ReplyCommand;
import dev.lerndmina.testplugin.events.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;


public final class Main extends JavaPlugin implements Listener {
    private GunToggleCommand gunToggleCommand;
    private HashMap<UUID, UUID> recentMessages;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin loaded pog");

        // Register Events
        Bukkit.getPluginManager().registerEvents(new JoinLeaveListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ToggleListener(this), this);
        Bukkit.getPluginManager().registerEvents(new FireworkListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GunListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ElytraFallListener(this), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(this), this);

        // Register commands
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("test").setExecutor(new TemplateCommand(this));
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("fireworks").setExecutor(new FireworkCommand(this));
        getCommand("debug").setExecutor(new DebugCommand(this));
        getCommand("elytra").setExecutor(new GiveElytraCommand(this));
        getCommand("book").setExecutor(new BookCommand(this));
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("punish").setExecutor(new PunishCommand(this));
        getCommand("menu").setExecutor(new MenuCommand(this));

        // Messages
        getCommand("pm").setExecutor(new PrivateMessageCommand(this));
        getCommand("r").setExecutor(new ReplyCommand(this));

        // Gun toggle stuff
        gunToggleCommand = new GunToggleCommand(this);
        getCommand("guntoggle").setExecutor(gunToggleCommand);

        // Initialise storage
        recentMessages = new HashMap<>();

        // Create config file and copy defaults
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin unloaded not pog");
    }


    public HashMap<UUID, UUID> getRecentMessages(){
        return recentMessages;
    }
    @EventHandler
    void onPLayerLeave(PlayerQuitEvent e){
        recentMessages.remove(e.getPlayer().getUniqueId());
    }


    /*
     *
     * !!! VAR STORAGE !!!
     *
     */

    public boolean fireworkEnabled = false; // Global firework toggle

    // TODO: convert to list so that each player can have their own toggle
    public boolean debug = false;

    public boolean isGunEnabled (Player player){
       return gunToggleCommand.isListed(player);
    }
}