package dev.lerndmina.thalwyrnthings.Utils;

import com.google.gson.Gson;
import dev.lerndmina.thalwyrnthings.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class JSONUtils {
    Main main = Main.getInstance();
    public ArrayList<UUID> loadUUIDListFromFile(String filename, String friendlyName) {
        ArrayList<UUID> uuidlist = convertToUUIDList(loadList(filename));
        if (uuidlist != null) {
            main.getLogger().info(uuidlist.size() + " " + friendlyName + " loaded!");
            return uuidlist;
        } else {
            main.getLogger().info("0 " + friendlyName + " loaded!");
            return new ArrayList<>();
        }
    }

    public ArrayList<String> convertToStringList(ArrayList<UUID> list){
        if (list.size() > 0){
            ArrayList<String> slist = new ArrayList<String>(list.size());
            for (UUID uuid : list){
                slist.add(uuid.toString());
            }
            return slist;
        }
        return null;
    }

    public ArrayList<UUID> convertToUUIDList(ArrayList<String> list){
        if (list == null){
            return null;
        }
        if (list.size() > 0 && list != null && list.get(0) != null){
            ArrayList<UUID> ulist = new ArrayList<UUID>(list.size());
            for (String str : list){
                try {
                    ulist.add(UUID.fromString(str));
                } catch (IllegalArgumentException e){
                    main.getLogger().info("Invalid UUID in list: " + str);
                }
            }
            return ulist;
        }
        return null;
    }

    public void saveList(ArrayList<String> arr, String filename){
        Gson gson = new Gson();
        File file = new File(main.getDataFolder() + "/" + filename);
        main.getLogger().info(main.getDataFolder().toString());
        try {
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            gson.toJson(arr, writer);
            writer.flush();
            writer.close();
            main.getLogger().info(filename + " saved.");
        } catch (Exception e) {
            main.getLogger().severe("Failed to create or save " + filename);
            main.getLogger().severe(e.toString());
        }
    }

    public ArrayList<String> loadList(String filename){
        Gson gson = new Gson();
        File file = new File (main.getDataFolder() + "/" + filename);
        if (file.exists()) {
            Reader reader = null;
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ArrayList<String> arrlist = gson.fromJson(reader, ArrayList.class);
            return arrlist;
        }
        return null;
    }
}
