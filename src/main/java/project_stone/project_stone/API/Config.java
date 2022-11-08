package project_stone.project_stone.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitScheduler;
import project_stone.project_stone.Project_stone;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Config {
    /*config---api---------------------------------------------------------*/
    public static void setConfig() {
        Project_stone.getPlugin().saveConfig();
    }
    public static FileConfiguration getConfig() {
        return Project_stone.getPlugin().getConfig();
    }
    public static String getValue(String path) {
        if(getConfig().get(path) != null) return Objects.requireNonNull(getConfig().get(path)).toString();
        else return "false";
    }
    public static void saveConfig() {
        Project_stone.getPlugin().saveConfig();
    }
    public static void reloadFile() {
        Project_stone.getPlugin().reloadConfig();
    }
    /*config---methods---------------------------------------------------------*/
    public static void savePointToPlayer(Player player, Location target, String index) {
        getConfig().set(player.getUniqueId()+"."+index+".world", Objects.requireNonNull(target.getWorld()).getName());
        getConfig().set(player.getUniqueId()+"."+index+".X",+target.getBlockX());
        getConfig().set(player.getUniqueId()+"."+index+".Y",+target.getBlockY());
        getConfig().set(player.getUniqueId()+"."+index+".Z",+target.getBlockZ());
        saveConfig();
    }
    public static Location getPointFromPlayer(Player player,String index) {
        doLater(10L,() -> {
            getConfig().set(player.getUniqueId()+"."+index, null);
            saveConfig();
        });
        return new Location(
                Bukkit.getWorld(getValue(player.getUniqueId()+"."+index+".world")),
                Double.parseDouble(getValue(player.getUniqueId()+"."+index+".X")),
                Double.parseDouble(getValue(player.getUniqueId()+"."+index+".Y")),
                Double.parseDouble(getValue(player.getUniqueId()+"."+index+".Z"))
        );
    }
    public static void removePointFromPlayer(Player player,String index) {
        getConfig().set(player.getUniqueId()+"."+index, null);
        saveConfig();
    }
    /*item---usage---------------------------------------------------------------*/
    public static boolean isInteractBlockWithMainHandOn(PlayerInteractEvent event) {
        if(EquipmentSlot.HAND.equals(event.getHand())){
            return event.hasBlock();
        }
        return false;
    }
    public static String getLocationXYZ(Location p) {
        String result = "";
        result += ChatColor.RED + " " + Math.round(p.getX()*100.0)/100.0;
        result += ChatColor.GREEN + " " + Math.round(p.getY()*100.0)/100.0;
        result += ChatColor.BLUE + " " + Math.round(p.getZ()*100.0)/100.0;
        result += ChatColor.RESET;
        return result;
    }
    public static void setTriggeredToPlayer(Player player,String index) {
        getConfig().set(player.getUniqueId()+".Triggered."+index,true);
        saveConfig();
        doLater(5L,() -> {
            getConfig().set(player.getUniqueId()+".Triggered."+index,false);
            saveConfig();
        });
    }
    public static boolean getTriggeredFromPlayer(Player player,String index) {
        return Boolean.parseBoolean(getValue(player.getUniqueId()+".Triggered."+index));
    }
    /*---------------------------------------------------------------*/
    public static List<String> getPlayerList() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        List<String> names = new ArrayList<>();
        for (Player p : players) {
            names.add(p.getName());
        }
        return names;
    }
    public static boolean match(String x, String y) {
        return x.equalsIgnoreCase(y);
    }
    public static boolean match(String x, String[] y) {
        for (String s : y) {
            if (x.equalsIgnoreCase(s)) return true;
        }
        return false;
    }
    public static List<String> playerNames() {
        return  Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
    }
    public static void doLater(Long time,Runnable content) {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskLater(Project_stone.getPlugin(),content,time);
    }
}
