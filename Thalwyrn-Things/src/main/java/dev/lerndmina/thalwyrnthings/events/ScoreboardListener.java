package dev.lerndmina.thalwyrnthings.events;

import dev.lerndmina.thalwyrnthings.Main;
import dev.lerndmina.thalwyrnthings.Utils.FastOfflinePlayer;
import static dev.lerndmina.thalwyrnthings.Utils.StringHelpers.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScoreboardListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        buildScoreboard(event.getPlayer());
    }

    private static ScoreboardListener listener;
    public static ScoreboardListener getListener(){
        if (listener == null){
            listener = new ScoreboardListener();
        }
        return listener;
    }

    public void buildScoreboard(Player player) {
        Main main = Main.getInstance();
        if (main.scoreboardList.contains(player.getUniqueId())){
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            return;
        }
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        String scoreboardTitle = main.getConfig().getString("scoreboard-title");

        if (scoreboardTitle == null){
            scoreboardTitle = "&cNo Title";
            main.getLogger().warning("Please set your scoreboard-title in the config.");
        }


        Objective objective = scoreboard.registerNewObjective("Thalwyrn", Criteria.DUMMY, Component.text(parseString(scoreboardTitle, player)));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        List<String> scoreboardContent = main.getConfig().getStringList("scoreboard-lines");
        if (scoreboardContent.isEmpty()){
            scoreboardContent.add("No content in config.");
            main.getLogger().warning("Please set your scoreboard-lines in the config.");
        }
        if(scoreboardContent.size() > 15){
            scoreboardContent = scoreboardContent.subList(0, 15);
            main.getLogger().warning("Scoreboard content is too long. Only the first 15 lines will be displayed.");
        }


        Collections.reverse(scoreboardContent);
        for (int i = 0; i < scoreboardContent.size(); i++ ){
            FastOfflinePlayer offlinePlayer = new FastOfflinePlayer(getColourForName(i));
            Team team = scoreboard.registerNewTeam("Line " + i);
            team.prefix(Component.text(parseString(scoreboardContent.get(i), player)));
            team.addPlayer(offlinePlayer);
            objective.getScore(offlinePlayer).setScore(i);
        }
        player.setScoreboard(scoreboard);

        try {
            List<String> finalScoreboardContent = scoreboardContent;
            String finalScoreboardTitle = scoreboardTitle;
            new BukkitRunnable(){
                public void run(){
                    if (!player.isOnline()){
                        this.cancel();
                        return;
                    }

                    if(main.scoreboardList.contains(player.getUniqueId())){
                        consoleMsg("Somehow a player who has their scoreboard disabled, had it refresh", Main.consoleTypes.WARN);
                        this.cancel();
                        return;
                    }

                    scoreboard.getObjective(DisplaySlot.SIDEBAR).displayName(Component.text(parseString(finalScoreboardTitle, player)));

                    for (int i = 0; i < finalScoreboardContent.size(); i++ ){
                        Team team = scoreboard.getTeam("Line " + i);
                        team.prefix(Component.text(parseString(finalScoreboardContent.get(i), player)));
                    }
                }
            }.runTaskTimer(Main.getInstance(), 23, 23); // Delay in ticks 4 scoreboard refresh.
        } catch (Exception e){
            consoleMsg("Cringe runnable threw an error", Main.consoleTypes.SEVERE);
            consoleMsg(Arrays.toString(e.getStackTrace()), Main.consoleTypes.SEVERE);

        }
    }






    public String getColourForName(int i){
        if (i < 9){
            return "§" + i;
        }
        switch (i){
            case 10:
                return "§a";
            case 11:
                return "§b";
            case 12:
                return "§c";
            case 13:
                return "§d";
            case 14:
                return "§e";
            default:
                return "§f";
        }
    }
}
