package dev.lerndmina.thalwyrnthings;
import dev.lerndmina.thalwyrnthings.Utils.JSONUtils;
import dev.lerndmina.thalwyrnthings.Utils.LogFilter;
import dev.lerndmina.thalwyrnthings.commands.ScoreboardCommand;
import dev.lerndmina.thalwyrnthings.commands.parrotGlue;
import dev.lerndmina.thalwyrnthings.commands.TemplateCommand;
import dev.lerndmina.thalwyrnthings.events.ScoreboardListener;
import dev.lerndmina.thalwyrnthings.events.parrotGlueListeners;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
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

        // Set the default config ;)
        saveDefaultConfig();

        LogFilter filter = new LogFilter(this);
        filter.registerFilter();

        // Don't delete this retard. You'll forget
        // new CommandName(this);
        new TemplateCommand(this);
        new ScoreboardCommand(this);
        new parrotGlue(this);


        this.getServer().getPluginManager().registerEvents(new parrotGlueListeners(), this);
        this.getServer().getPluginManager().registerEvents(new ScoreboardListener(), this);





        parrotList = utils.loadUUIDListFromFile(parrotListFileName, "Parrot Glue UUIDs");
        scoreboardList = utils.loadUUIDListFromFile(scoreboardListFile, "Scoreboard UUIDs");

        Bukkit.getServer().getOnlinePlayers().stream().filter(player -> {
            ScoreboardListener.getListener().buildScoreboard(player);
            return false;
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        utils.saveList(utils.convertToStringList(parrotList), parrotListFileName);
        utils.saveList(utils.convertToStringList(scoreboardList), scoreboardListFile);

        Bukkit.getServer().getOnlinePlayers().stream().filter(player -> {
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            return false;
        });
    }

    public boolean debug = this.getConfig().getBoolean("debug-toggle");

    public ArrayList<UUID> parrotList = new ArrayList<>();
    private String parrotListFileName = "parrotGlueList.json";

    public ArrayList<UUID> scoreboardList = new ArrayList<>();
    private String scoreboardListFile = "scoreboardList.json";

    public enum consoleTypes{
        INFO, WARN, SEVERE
    }


}
