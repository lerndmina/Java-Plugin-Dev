package dev.lerndmina.thalwyrnthings.Utils;

import dev.lerndmina.thalwyrnthings.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class Command extends BukkitCommand{
    protected final Main main; // Define main class

    boolean isPlayerNeeded;
    String commandPermission;
    String noPermissionMessage;


    protected Command(Main main, boolean requiresPlayer ,@NotNull String command, String description, String usage, String permission, String[] aliases) {
        super(command);
        this.main = main;
        this.setAliases(Arrays.asList(aliases));
        this.setDescription(description);
        this.setUsage(usage);
        commandPermission = permission;
//        this.setPermission(permission);
        noPermissionMessage = ("You don't have permission \"" + permission + "\"");
        isPlayerNeeded = requiresPlayer;


        try{ // Lmao we have to break our way into bukkit to force register the command without the BS in plugin.yml
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap map = (CommandMap) field.get(Bukkit.getServer());
            map.register(command, this);
        } catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission(commandPermission) || commandPermission.equals("")){
                executePlayer(player,args);
            } else {
                playerMsg(player, "&cYou lack the permission for this command. '" + commandPermission + "'");
            }
        } else if (!isPlayerNeeded) {
            executeConsole(sender,args);
        } else {
            consoleMsg("&cThis command must be ran by a player!", Main.consoleTypes.WARN) ;
        }
        return true;
    }

    public abstract void executeConsole(CommandSender console, String[] args);

    public abstract void executePlayer(Player player, String[] args);


    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return onTabComplete(sender, args);
    }

    public abstract List<String> onTabComplete(CommandSender sender, String[] args);




    // Helper classes

    public static final int GUI_WIDTH = 9;

    // Grab things from the config file
    public String configString(String path) {
        return parseColor(main.getConfig().getString(path));
    }

    public Integer configInt(String path) {
        return main.getConfig().getInt(path);
    }

    public Boolean configBool(String path) {
        return main.getConfig().getBoolean(path);
    }

    // Equalsignorecase for strings
    public boolean listCompare(String needle, String... haystack) {
        for (String strings : haystack) {
            if (needle.equalsIgnoreCase(strings)) {
                return true;
            }
        }
        return false;
    }

    public String stringFromArgs(String[] arr, Integer arrPos) {
        StringBuilder builder = new StringBuilder();
        for (int index = arrPos; index < arr.length; index++) {
            builder.append(arr[index]).append(" ");
        }
        return parseColor(builder.toString());
    }

    public void playerMsg(Player player, String message) {
        player.sendMessage(parseColor(main.getConfig().getString("prefix") + "&f " + message));
    }
    public void playerClean(Player player, String message) {
        player.sendMessage(parseColor(message));
    }

    public void consoleMsg(String message, Main.consoleTypes type){
        if(type == Main.consoleTypes.INFO){
            main.getLogger().info(parseColor(message));
        } else if (type == Main.consoleTypes.WARN) {
            main.getLogger().warning(parseColor(message));
        } else if (type == Main.consoleTypes.SEVERE) {
            main.getLogger().severe(parseColor(message));
        }
    }

    public void debugPlayerMsg(Player p, String message) {
        if (main.debug) {
            p.sendMessage(parseColor("&c&lDEBUG &4&l>> &c" + message));
        }
    }

    public static String parseColor(String content) {
        return (ChatColor.translateAlternateColorCodes('&', content));
    }

    // !!! ITEM CHECKS !!!
    public boolean hasItemInHand(Player p, Material material) {
        return p.getInventory().getItemInMainHand().getType().equals(material);
        // Returns true if the item in the players hand is the same as the material
    }
}