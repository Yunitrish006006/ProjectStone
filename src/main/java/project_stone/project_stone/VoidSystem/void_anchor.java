package project_stone.project_stone.VoidSystem;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import project_stone.project_stone.BasicStone;
import project_stone.project_stone.Project_stone;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class void_anchor implements CommandExecutor {
    private static File file;
    private static FileConfiguration config;
    public static void setup() {
        file = new File(Project_stone.getPlugin().getDataFolder(),"anchors.yml");
        if(!file.exists()) {
            try {
                boolean x = file.createNewFile();
                if(!x) {
                    Project_stone.getPlugin().getServer().broadcastMessage("initialized..");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    public static void save() {
        try {
            void_anchor.get().save(file);
        } catch (IOException e) {
            System.out.println("file can't save");
        }
    }
    public static FileConfiguration get() {
        return config;
    }
    public static void reload() { config = YamlConfiguration.loadConfiguration(file); }
    public static void initialize() {
        void_anchor.setup();
        void_anchor.save();
    }
    public static String LocationString(Player player) {
        String result = "";
        result += ChatColor.GOLD + " " + Objects.requireNonNull(player.getLocation().getWorld()).getName() + ChatColor.WHITE  + " , ";
        result += ChatColor.RED + " " + Math.round(player.getLocation().getX()*100.0)/100.0 + ChatColor.WHITE  + " , ";
        result += ChatColor.GREEN + " " + Math.round(player.getLocation().getY()*100.0)/100.0 + ChatColor.WHITE  + " , ";
        result += ChatColor.BLUE + " " + Math.round(player.getLocation().getZ()*100.0)/100.0;
        return result;
    }
    public static List<String> getAnchors(Player player) {
        ConfigurationSection player_config = config.getConfigurationSection(player.getUniqueId().toString());
        String[] keys = Objects.requireNonNull(player_config).getKeys(false).toArray(new String[config.getKeys(false).size()]);
        return new ArrayList<>(Arrays.asList(keys));
    }
    public static Runnable teleportCount(Player player,int countDown,Sound sound,Location location) {
        return () -> {
            if(location.equals(player.getLocation())) {
                player.sendTitle("" + countDown, ChatColor.MAGIC + "_____",10,10,10);
                player.playSound(player.getLocation(),sound, SoundCategory.MASTER,0.4f,1.0f);
            }
        };
    }
    public static Runnable toAnchor(final String type,Player player,String name,Location location) {
        return () -> {
            if(location.equals(player.getLocation())) {
                if(BasicStone.same(type,"anchor")) {
                    player.teleport(void_anchor.getAnchor(player, name));
                    player.playSound(player.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.HOSTILE, 1.0f, 0.2f);
                    player.sendTitle(ChatColor.GOLD + name, ChatColor.MAGIC + "_________", 10, 10, 10);
                }
                else if(BasicStone.same(type,"player")) {
                    player.teleport(Objects.requireNonNull(Bukkit.getPlayer(name)));
                    player.playSound(player.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.HOSTILE, 1.0f, 0.2f);
                    player.sendTitle(ChatColor.GOLD + name, ChatColor.MAGIC + "_________", 10, 10, 10);
                }
            }

        };
    }
    public static void delayToAnchor(Player player,String name,int delayTime,String type) {
        Location location = player.getLocation();
        for(int i=0;i<delayTime;i++) {
            BasicStone.doLater(i*20L,teleportCount(player,delayTime-i,Sound.ENTITY_ARROW_HIT_PLAYER,location));
        }
        BasicStone.doLater(delayTime*20L,toAnchor(type,player,name,location));
    }
    public static Location getAnchor(Player player, String name) {
        if(config.get(player.getUniqueId()+"."+name)==null) {
            player.sendMessage(ChatColor.RED + "can not found " + ChatColor.GOLD + "\"" + name + "\"");
        }
        return new Location(
                Bukkit.getServer().getWorld(Objects.requireNonNull(config.get(player.getUniqueId() + "." + name + ".world")).toString()),
                Double.parseDouble(Objects.requireNonNull(config.get(player.getUniqueId() + "." + name + ".X")).toString()),
                Double.parseDouble(Objects.requireNonNull(config.get(player.getUniqueId() + "." + name + ".Y")).toString()),
                Double.parseDouble(Objects.requireNonNull(config.get(player.getUniqueId() + "." + name + ".Z")).toString()),
                Float.parseFloat(Objects.requireNonNull(config.get(player.getUniqueId() + "." + name + ".yaw")).toString()),
                Float.parseFloat(Objects.requireNonNull(config.get(player.getUniqueId() + "." + name + ".pitch")).toString())
        );
    }
    public static void addAnchor(Player player,String name) {
        config.set(player.getUniqueId() + "." + name + ".world", Objects.requireNonNull(player.getLocation().getWorld()).getName());
        config.set(player.getUniqueId() + "." + name + ".X",player.getLocation().getX());
        config.set(player.getUniqueId() + "." + name + ".Y",player.getLocation().getY());
        config.set(player.getUniqueId() + "." + name + ".Z",player.getLocation().getZ());
        config.set(player.getUniqueId() + "." + name + ".yaw",player.getLocation().getYaw());
        config.set(player.getUniqueId() + "." + name + ".pitch",player.getLocation().getPitch());
        void_anchor.save();
        player.sendMessage("You had set " + ChatColor.GOLD + name + ChatColor.WHITE + " as home at" + LocationString(player));
    }
    public static void deleteAnchor(Player player, String name) {
        ConfigurationSection temp = config.getConfigurationSection(player.getUniqueId().toString());
        Set<String> set = Objects.requireNonNull(temp).getKeys(false);
        Set<String> toRemove = new HashSet<>();
        boolean found = false;
        for (String s : set) { if (s.equals(name)) { toRemove.add(s); found = true; }}
        if(found) { player.sendMessage("You had removed " + ChatColor.GOLD + name); }
        else { player.sendMessage(ChatColor.RED + "can not found " + ChatColor.GOLD + "\"" + name + "\""); }
        for (String s : toRemove) { temp.set(s, null); }
    }
    public static void listAnchor(Player player) {
        ConfigurationSection temp = config.getConfigurationSection(player.getUniqueId().toString());
        String[] keys = Objects.requireNonNull(temp).getKeys(false).toArray(new String[temp.getKeys(false).size()]);
        List<String> homes = new ArrayList<>(Arrays.asList(keys));
        for (String home : homes) {
            ConfigurationSection currentAnchor = config.getConfigurationSection(player.getUniqueId() + "." + home);
            player.sendMessage(
                    ChatColor.GOLD + home
                    + ChatColor.WHITE + " : " + Objects.requireNonNull(currentAnchor).getString(".world")
                    + ChatColor.RED + " " + Math.round(currentAnchor.getDouble(".X")*100.0)/100.0
                    + ChatColor.GREEN + " " + Math.round(currentAnchor.getDouble(".Y")*100.0)/100.0
                    + ChatColor.BLUE + " " + Math.round(currentAnchor.getDouble(".Z")*100.0)/100.0
            );
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            switch (args.length){
                case 0:
                    player.sendMessage(ChatColor.GOLD + "help:");
                    player.sendMessage("    " + Project_stone.getPlugin().getName());
                    player.sendMessage("    " + Project_stone.getRootFolder());
                    return true;
                case 1:
                    if(BasicStone.same(args[0],"list")) {
                        listAnchor(player);
                        return true;
                    }
                case 2:
                    if(BasicStone.same(args[0],"to")) {
                        delayToAnchor(player,args[1],4,"Anchor");
                        return true;
                    }
                    else if(BasicStone.same(args[0],"player")) {
                        deleteAnchor(player,args[1]);
                        return true;
                    }
                    else if(BasicStone.same(args[0],"set")) {
                        addAnchor(player,args[1]);
                        return true;
                    }
                    else if(BasicStone.same(args[0],"remove")) {
                        deleteAnchor(player,args[1]);
                        return true;
                    }
            }
        }
        return false;
    }
}
