package dev.lerndmina.testplugin;

import com.google.gson.Gson;
import dev.lerndmina.testplugin.commands.*;
import dev.lerndmina.testplugin.commands.privateMessage.PrivateMessageCommand;
import dev.lerndmina.testplugin.commands.privateMessage.ReplyCommand;
import dev.lerndmina.testplugin.events.*;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public final class Main extends JavaPlugin implements Listener {

    private HashMap<UUID, UUID> recentMessages;


    @Override
    public void onEnable() {

        getLogger().info("Plugin loaded pog");

        // Register Events
        Bukkit.getPluginManager().registerEvents(new JoinLeaveListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatToggleListener(this), this);
        Bukkit.getPluginManager().registerEvents(new FireworkListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ElytraFallListener(this), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(this), this);
        Bukkit.getPluginManager().registerEvents(new FlyBoostListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PunishMutedListener(this), this);
        Bukkit.getPluginManager().registerEvents(new CommandLogEvent(this), this);

        // Register commands
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("heal").setTabCompleter(new HealCommand(this));

        getCommand("test").setExecutor(new TemplateCommand(this));
        getCommand("test").setTabCompleter(new TemplateCommand(this));

        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("vanish").setTabCompleter(new VanishCommand(this));

        getCommand("fireworks").setExecutor(new FireworkCommand(this));
        getCommand("fireworks").setTabCompleter(new FireworkCommand(this));

        getCommand("debug").setExecutor(new DebugCommand(this));
        getCommand("debug").setTabCompleter(new DebugCommand(this));

        getCommand("elytra").setExecutor(new GiveElytraCommand(this));
        getCommand("elytra").setTabCompleter(new GiveElytraCommand(this));

        getCommand("book").setExecutor(new BookCommand(this));
        getCommand("book").setTabCompleter(new BookCommand(this));

        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("fly").setTabCompleter(new FlyCommand(this));

        getCommand("punish").setExecutor(new PunishCommand(this));
        getCommand("punish").setTabCompleter(new PunishCommand(this));

        getCommand("menu").setExecutor(new MenuCommand(this));
        getCommand("menu").setTabCompleter(new MenuCommand(this));

        getCommand("fruit").setExecutor(new FruitCommand(this));
        getCommand("fruit").setTabCompleter(new FruitCommand(this));

        getCommand("flyboost").setExecutor(new FlyBoostCommand(this));
        getCommand("flyboost").setTabCompleter(new FlyBoostCommand(this));

        // Messages
        getCommand("pm").setExecutor(new PrivateMessageCommand(this));
        getCommand("pm").setTabCompleter(new PrivateMessageCommand(this));

        getCommand("r").setExecutor(new ReplyCommand(this));
        getCommand("r").setTabCompleter(new ReplyCommand(this));

        getCommand("oogabooga").setExecutor(new OogaBoogaCommand(this));
        getCommand("oogabooga").setTabCompleter(new OogaBoogaCommand(this));

        getCommand("log").setExecutor(new LogCommand(this));
        getCommand("log").setTabCompleter(new LogCommand(this));

        getCommand("map").setExecutor(new GiveCustomMapCommand(this));
        getCommand("map").setTabCompleter(new GiveCustomMapCommand(this));


        // Initialise storage
        recentMessages = new HashMap<>();

        cmdLog = loadList("cmdLog.json");
        if (cmdLog == null) {
            cmdLog = new ArrayList<>();
        }
        getLogger().info("Loaded " + cmdLog.size() + " entries from the log");

        muted = loadUUIDListFromFile("muted.json", "Muted Players");

        // Create config if needed file and copy defaults
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // save data to config file

//        saveList(convertToStringList(muted), "muted.json");

        saveList(cmdLog, "cmdLog.json");

        getLogger().info("Plugin unloaded not pog");
    }

    public ArrayList<UUID> loadUUIDListFromFile(String filename, String friendlyName) {
        ArrayList<UUID> uuidlist = convertToUUIDList(loadList(filename));
        if (uuidlist != null) {
            getLogger().info(uuidlist.size() + " " + friendlyName + " loaded!");
            return uuidlist;
        } else {
            getLogger().info("0 " + friendlyName + " loaded!");
            return new ArrayList<>();
        }
    }

    public ArrayList<String> convertToStringList(ArrayList<UUID> list){
        if (list.size() > 0){
            ArrayList<String> slist = new ArrayList<String>(list.size());
            for (UUID uuid : list){
                slist.add(uuid.toString());
            }
            return slist;
        }
        return null;
    }

    public ArrayList<UUID> convertToUUIDList(ArrayList<String> list){
        if (list == null){
            return null;
        }
        if (list.size() > 0 && list != null && list.get(0) != null){
            ArrayList<UUID> ulist = new ArrayList<UUID>(list.size());
            for (String str : list){
                try {
                    ulist.add(UUID.fromString(str));
                } catch (IllegalArgumentException e){
                    getLogger().info("Invalid UUID in list: " + str);
                }
            }
            return ulist;
        }
        return null;
    }

    public void saveList(ArrayList<String> arr, String filename){
        Gson gson = new Gson();
        File file = new File(getDataFolder() + "/" + filename);
        getLogger().info(getDataFolder().toString());
        try {
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            gson.toJson(arr, writer);
            writer.flush();
            writer.close();
            getLogger().info(filename + " saved.");
        } catch (Exception e) {
            getLogger().severe("Failed to create or save " + filename);
            getLogger().severe(e.toString());
        }
    }

    public ArrayList<String> loadList(String filename){
        Gson gson = new Gson();
        File file = new File (getDataFolder() + "/" + filename);
        if (file.exists()) {
            Reader reader = null;
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ArrayList<String> arrlist = gson.fromJson(reader, ArrayList.class);
           return arrlist;
        }
        return null;
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
    public boolean debug = true;

    // list of players with flight enabled
    public ArrayList<UUID> flightEnabled = new ArrayList<>();

    // list of players about to generate a map
    public ArrayList<UUID> mapCache = new ArrayList<>();

    // list of muted players
    public ArrayList<UUID> muted = new ArrayList<>();

    // Log arraylist
    public ArrayList<String> cmdLog = new ArrayList<>();

    public void saveMutes(){
        saveList(convertToStringList(muted), "muted.json");
    }

    public ArrayList<UUID> chatDisabled = new ArrayList<>();


}


