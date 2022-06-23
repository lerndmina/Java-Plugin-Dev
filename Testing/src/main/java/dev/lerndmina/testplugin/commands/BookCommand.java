package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BookCommand extends AbstractCommand {

    public BookCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = isPlayerAndHasPermission(sender, "wild.command.givebook", false); // Check if player is online and has permission
        if (player != null) { // Execute the command and run this code.

            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta meta = (BookMeta) book.getItemMeta();
            meta.setTitle(configString("book.title"));
            meta.setAuthor(configString("book.author"));
            List<String> pages = main.getConfig().getStringList("book.pages");
            meta.setPages(pages);

            book.setItemMeta(meta);
            player.getInventory().addItem(book);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}