package dev.lerndmina.testplugin.commands;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;

public class GiveCustomMapCommand extends AbstractCommand {

    public GiveCustomMapCommand(Main main) { // Import main for use in this class
        super(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = isPlayerAndHasPermission(sender, "wild.command.CustomMap", false);
        if (p != null) { // Execute the command and run this code.
            sendMessage(p, stringFromArgs(args, 0));
            ItemStack mapItem = new ItemStack(Material.FILLED_MAP);
            MapMeta mapMeta = (MapMeta) mapItem.getItemMeta();
            MapView mapView = Bukkit.createMap(p.getWorld());

            mapView.addRenderer(new MapRenderer() {
                @Override
                public void render(@NotNull MapView mapView, @NotNull MapCanvas mapCanvas, @NotNull Player player) {
                    try {
                        BufferedImage image = ImageIO.read(new URL("https://i.lerndmina.dev/images/png/MKF5x.png"));
                        mapCanvas.drawImage(0,0,image);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            mapMeta.setMapView(mapView);
            mapItem.setItemMeta(mapMeta);
            p.getInventory().addItem(mapItem);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return null;
    }
}