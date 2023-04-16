package dev.lerndmina.thalwyrnthings;
import com.tchristofferson.configupdater.ConfigUpdater;
import dev.lerndmina.thalwyrnthings.Utils.JSONUtils;
import dev.lerndmina.thalwyrnthings.Utils.StringHelpers;
import dev.lerndmina.thalwyrnthings.commands.*;
import dev.lerndmina.thalwyrnthings.events.ElytraBoostListener;
import dev.lerndmina.thalwyrnthings.events.ScoreboardListener;
import dev.lerndmina.thalwyrnthings.events.parrotGlueListeners;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {
    private static Main instance;
    public static Main getInstance() {
        return instance;
    }
    JSONUtils utils;

    @Override
    public void onEnable() {
        instance = this;
        utils = new JSONUtils();

        if(!placeholderCheck()){
            StringHelpers.consoleMsg("Soft Dependency PlaceholderAPI not found. The plugin will make no attempt to load placeholders.", consoleTypes.WARN);
            return;
        } else {
            StringHelpers.consoleMsg("Soft Dependency PlaceholderAPI found. Placeholders will be parsed.", consoleTypes.INFO);
        }

        // Create and update the config
        saveDefaultConfig();
        File configFile = new File(getDataFolder(), "config.yml");

        try{
            ConfigUpdater.update(this, "config.yml", configFile, new ArrayList<>());
        } catch (IOException e){
            e.printStackTrace();
        }
        reloadConfig();
        StringHelpers.consoleMsg("Config loaded.", consoleTypes.INFO);


        // Don't delete this retard. You'll forget
        // new CommandName(this);
        new TemplateCommand(this);
        new ScoreboardCommand(this);
        new parrotGlue(this);
        new SlapCommand(this);
        new GlideBoostToggle(this);
        new ColourCommand(this);
        new ThalwyrnCommand(this);

        this.getServer().getPluginManager().registerEvents(new parrotGlueListeners(), this);
        this.getServer().getPluginManager().registerEvents(new ScoreboardListener(), this);
        this.getServer().getPluginManager().registerEvents(new ElytraBoostListener(), this);





        parrotList = utils.loadUUIDListFromFile(parrotListFileName, "Parrot Glue UUIDs");
        scoreboardDisabledList = utils.loadUUIDListFromFile(scoreboardDisabledListFile, "Scoreboard UUIDs");
        glideBoostList = utils.loadUUIDListFromFile(glideBoostListFile, "Glide Boost UUIDs");

        Bukkit.getServer().getOnlinePlayers().stream().filter(player -> {
            ScoreboardListener.getListener().buildScoreboard(player);
            return false;
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        utils.saveList(utils.convertToStringList(parrotList), parrotListFileName);
        utils.saveList(utils.convertToStringList(scoreboardDisabledList), scoreboardDisabledListFile);
        utils.saveList(utils.convertToStringList(glideBoostList), glideBoostListFile);


        Bukkit.getServer().getOnlinePlayers().stream().filter(player -> {
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            return false;
        });
    }

    public final int SCOREBOARD_MAX_TITLE_LENGTH = 128;
    public boolean placeholderCheck() {
        return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }
    public boolean debug = this.getConfig().getBoolean("debug-toggle");

    public ArrayList<UUID> parrotList = new ArrayList<>();
    private String parrotListFileName = "parrotGlueList.json";

    public ArrayList<UUID> scoreboardDisabledList = new ArrayList<>();
    private String scoreboardDisabledListFile = "scoreboardList.json";

    public ArrayList<UUID> glideBoostList = new ArrayList<>();
    public ArrayList<UUID> currentlyGlideBoosting = new ArrayList<>();
    private String glideBoostListFile = "glideBoost.json";

    public enum consoleTypes{
        INFO, WARN, SEVERE
    }


}
