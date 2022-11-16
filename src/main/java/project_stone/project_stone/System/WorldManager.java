package project_stone.project_stone.System;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import project_stone.project_stone.API.Config;
import project_stone.project_stone.API.TextAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorldManager implements Listener {
    public static List<World> worlds = Bukkit.getWorlds();
    public static void initial() {
        FileConfiguration configuration = Config.getConfig();
        if(Config.getConfig().get("Worlds")!=null) {
             List<String> names = new ArrayList<>(Objects.requireNonNull(Config.getConfig().getConfigurationSection("Worlds")).getKeys(false));
             for(String name : names) {
                 TextAPI.sendTest(name);
                 worlds.add(Bukkit.getWorld(name));
             }
        }
        else {
            worlds = Bukkit.getWorlds();
            for (World world : worlds) {
                configuration.set("Worlds."+world.getName(),true);
            }
        }
        Config.saveConfig();
    }
    public static void createWorld(String name){
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.type(WorldType.NORMAL);
        worldCreator.generateStructures(true);
        worlds.add(worldCreator.createWorld());
        for (World world : worlds) {
            Config.getConfig().set("Worlds."+world.getName(),true);
        }
        Config.saveConfig();
        Bukkit.reload();
    }

    public static void toWorldSpawn(Player player,World world) {
        player.teleport(world.getSpawnLocation());
    }

    public static List<String> getWorldList() {
        List<String> worldList = new ArrayList<>();
        List<World> worlds = Bukkit.getWorlds();
        for (World value : worlds) {
            worldList.add(value.getName());
        }
        return worldList;
    }
}
