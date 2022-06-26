package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.regex.Pattern;


public class CommandLogEvent extends AbstractEvent {
    public CommandLogEvent(Main main) {
        super(main);
    }

    int counter = 0;

    @EventHandler
    public void onCommandSend(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage();
        Player p = e.getPlayer();
        Pattern pattern = Pattern.compile(configString("log-regex"), Pattern.CASE_INSENSITIVE);

        if (pattern.matcher(command).find()) {
            // Don't log the matches and return
            return;
        }

        // get the current time pretty formatted
        String time = new java.text.SimpleDateFormat("hh:mma").format(new java.util.Date());

        String logEntry = parseColor("&7" + time + " &f" + e.getPlayer().getName() + ": " + "&e" + command);
        main.cmdLog.add(logEntry);

        while (main.cmdLog.size() > 16) {
            main.cmdLog.remove(0);
        }

        if (counter <= 5){
            counter++;
            return;
        }

        main.saveList(main.cmdLog, "cmdLog.json");
        counter = 0;
    }
}
