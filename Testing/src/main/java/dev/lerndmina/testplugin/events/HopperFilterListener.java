package dev.lerndmina.testplugin.events;

import dev.lerndmina.testplugin.Main;
import dev.lerndmina.testplugin.Utils.AbstractEvent;
import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class HopperFilterListener extends AbstractEvent {

    private final List<UUID> chatDisabled = new ArrayList<>();

    public HopperFilterListener(Main main) {
        super(main); // Import main for use in this class
        // Define main class
    }
    String getItemName(String translationKey){
        if(translationKey==null) return null;
        String[] names = translationKey.split("\\.");
        return names[names.length-1];
    }
    boolean filterMatch(String filterString, String fullItemName){
        String itemName = getItemName(fullItemName);
        String[] filter = filterString.split(",");
        return Arrays.stream(filter).anyMatch((filter_i) -> {
            if(filter_i.endsWith("*")) {
                return itemName.startsWith(filter_i.substring(0, filter_i.length() - 1));
            } else if (filter_i.startsWith("*")) {
                return itemName.endsWith(filter_i.substring(1));
            } else {
                return itemName.equalsIgnoreCase(filter_i);
            }
        });
    }

    @EventHandler
    void onInventoryMoveItem(InventoryMoveItemEvent event){
        if(event.getDestination().getType().equals(InventoryType.HOPPER)
            && event.getDestination().getHolder() instanceof Container){
            String hopperName = ((Container) event.getDestination().getHolder()).getCustomName();
            if(hopperName != null){
                String itemName = event.getItem().translationKey();
                if(!filterMatch(hopperName, itemName)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    void onInventoryPickupItem(InventoryPickupItemEvent event){
        if(event.getInventory().getHolder() instanceof Container){
            String hopperName = (((Container) event.getInventory().getHolder()).getCustomName());
            if(hopperName != null){
                String itemName = event.getItem().getItemStack().translationKey();
                if(!filterMatch(hopperName,itemName)){
                    event.setCancelled(true);
                }
            }
        }
    }
}