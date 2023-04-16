package dev.lerndmina.thalwyrnthings.Utils;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import dev.lerndmina.thalwyrnthings.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelpers {

    public static String parseString(String input){

        return IridiumColorAPI.process(input);
    }

    public static String parseString(String input, Player player){
        if (Main.getInstance().placeholderCheck()){
            input = PlaceholderAPI.setPlaceholders(player, input);
        }
        input = IridiumColorAPI.process(input);
        return input;
    }



    public static void playerMsg(Player player, String message) {
        player.sendMessage(StringHelpers.parseString(Main.getInstance().getConfig().getString("prefix") + "&f " + message, player));
    }
    public void playerClean(Player player, String message) {
        player.sendMessage(StringHelpers.parseString(message));
    }

    public static void consoleMsg(String message, Main.consoleTypes type){
        if(type == Main.consoleTypes.INFO){
            Main.getInstance().getLogger().info(StringHelpers.parseString(message));
        } else if (type == Main.consoleTypes.WARN) {
            Main.getInstance().getLogger().warning(StringHelpers.parseString(message));
        } else if (type == Main.consoleTypes.SEVERE) {
            Main.getInstance().getLogger().severe(StringHelpers.parseString(message));
        }
    }

    public static void debugPlayerMsg(Player p, String message) {
        if (Main.getInstance().debug) {
            p.sendMessage(StringHelpers.parseString("&c&lDEBUG &4&l>> &c" + message));
        }
    }

    public static void debugConsoleMsg(String message) {
        if (Main.getInstance().debug) {
            Main.getInstance().getLogger().warning("DEBUG >> " + message);
        }
    }
}
