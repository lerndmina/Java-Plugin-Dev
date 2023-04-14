package dev.lerndmina.thalwyrnthings.Utils;

import dev.lerndmina.thalwyrnthings.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.ChatColor.COLOR_CHAR;

public class StringHelpers {

    public static String parseString(String input){
        String output = parseColor(input);

        return output;
    }

    public static String parseString(String input, Player player){
        String output = parseColor(input);
        output = PlaceholderAPI.setPlaceholders(player, output);


        return output;
    }

    public static String parseColor(String content) {
        return (ChatColor.translateAlternateColorCodes('&', translateHexColorCodes("&#","", content)));
    }


    public static void playerMsg(Player player, String message) {
        player.sendMessage(StringHelpers.parseColor(Main.getInstance().getConfig().getString("prefix") + "&f " + message));
    }
    public void playerClean(Player player, String message) {
        player.sendMessage(StringHelpers.parseColor(message));
    }

    public static void consoleMsg(String message, Main.consoleTypes type){
        if(type == Main.consoleTypes.INFO){
            Main.getInstance().getLogger().info(StringHelpers.parseColor(message));
        } else if (type == Main.consoleTypes.WARN) {
            Main.getInstance().getLogger().warning(StringHelpers.parseColor(message));
        } else if (type == Main.consoleTypes.SEVERE) {
            Main.getInstance().getLogger().severe(StringHelpers.parseColor(message));
        }
    }

    public static String translateHexColorCodes(String startTag, String endTag, String message)
    {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "&"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

    public static void debugPlayerMsg(Player p, String message) {
        if (Main.getInstance().debug) {
            p.sendMessage(StringHelpers.parseColor("&c&lDEBUG &4&l>> &c" + message));
        }
    }

    public static void debugConsoleMsg(String message) {
        if (Main.getInstance().debug) {
            Main.getInstance().getLogger().warning("DEBUG >> " + message);
        }
    }
}
