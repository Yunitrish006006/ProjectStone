package project_stone.project_stone.VoidTech;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import project_stone.project_stone.Project_stone;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static project_stone.project_stone.API.Config.doLater;

public class Anchor {
    /*FILE=================================================================================*/
    public static File file;
    public static final String filename = "Anchor.yml";
    public static FileConfiguration config;
    public static void setFile(String filename) {
        file = new File(Project_stone.getPlugin().getDataFolder(),filename);
        if(!file.exists()) {
            try {
                if(!file.createNewFile()) {
                    Project_stone.getPlugin().getServer().broadcastMessage("initializing files");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration getConfig() {
        return config;
    }
    public static void saveFile() {
        try {
            getConfig().save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void reloadFile() { config = YamlConfiguration.loadConfiguration(file); }
    public static void initialize() {
        setFile(filename);
        saveFile();
    }
    /*constructor==========================================================================*/
    private String owner;
    private String uuid;
    private boolean gravity;
    private String purview;
    private int wait_time;
    private String world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private String anchor_name;
    public Anchor() {
        world = "world";
        x = 0;
        y = 0;
        z = 0;
        yaw = 0;
        pitch = 0;
        gravity = false;
        purview = "none";
        anchor_name = "temp";
        wait_time = 0;
        owner = "world";
        uuid = UUID.randomUUID().toString();
    }
    public Anchor(String name,Location location) {
        world = Objects.requireNonNull(location.getWorld()).getName();
        x = location.getX();
        y = location.getY();
        z = location.getZ();
        yaw = location.getYaw();
        pitch = location.getPitch();
        gravity = false;
        purview = "public";
        anchor_name = name;
        wait_time = 3;
        owner = "world";
        uuid = UUID.randomUUID().toString();
    }
    public Anchor(String name,Player player) {
        world = player.getWorld().getName();
        x = player.getLocation().getX();
        y = player.getLocation().getY();
        z = player.getLocation().getZ();
        yaw = player.getLocation().getYaw();
        pitch = player.getLocation().getPitch();
        gravity = false;
        purview = "private";
        anchor_name = name;
        wait_time = 3;
        owner = player.getName();
        uuid = player.getUniqueId().toString();
    }
    /*method===============================================================================*/
    public Anchor canEdit(Player player) {
        if(purview.equalsIgnoreCase("none")) {
            player.sendMessage(ChatColor.RED+"You have no permission to do this action!");
            player.sendMessage(ChatColor.MAGIC+"我肏你媽");
            return new Anchor();
        }
        if(uuid.equalsIgnoreCase(player.getUniqueId().toString())) return this;
        else {
            Objects.requireNonNull(Bukkit.getPlayer(owner)).sendMessage(ChatColor.RED + "Can't edit, please contact the owner of Anchor");
            return null;
        }
    }
    public Anchor canTeleport(Player player) {
        if(purview.equalsIgnoreCase("none")) {
            player.sendMessage(ChatColor.RED+"You have no permission to do this action!");
            player.sendMessage(ChatColor.MAGIC+"我肏你媽");
            return new Anchor();
        }
        if(uuid.equalsIgnoreCase(player.getUniqueId().toString()) || purview.equalsIgnoreCase("public")) return this;
        return null;
    }

    public void setGravity(boolean value) {
        if(world.equalsIgnoreCase("world")) {
            gravity = value;
            add();
            Objects.requireNonNull(Bukkit.getPlayer(owner)).sendMessage(ChatColor.GREEN + "Set"+anchor_name+"'s Gravity to True");
        }
        Objects.requireNonNull(Bukkit.getPlayer(owner)).sendMessage(ChatColor.RED + "Can't set gravity in "+world+"!");
    }
    public void setWait_time(int value) {
        Objects.requireNonNull(Bukkit.getPlayer(owner)).sendMessage(ChatColor.GREEN + "Set"+anchor_name+"'s WaitTime to " + value);
        wait_time = value;
        add();
    }
    public void setPurview(String value) {
        Objects.requireNonNull(Bukkit.getPlayer(owner)).sendMessage(ChatColor.GREEN + "Set"+anchor_name+"'s Purview to " + value);
        purview = value;
        add();
    }
    public void setAnchor_name(String value) {
        del();
        Objects.requireNonNull(Bukkit.getPlayer(owner)).sendMessage(ChatColor.GREEN + "Update"+anchor_name+"'s name to " + value);
        anchor_name = value;
        add();
    }

    public boolean isGravity() {
        return gravity;
    }
    public int getWait_time() {
        return wait_time;
    }
    public String getPurview() {
        return purview;
    }
    public String getAnchor_name() {
        return anchor_name;
    }
    public String getPosition() {
        return  "("+Math.round(x*100.0)/100.0+","+Math.round(y*100.0)/100.0+","+Math.round(z*100.0)/100.0+")";
    }
    public String getLocationInformation() {
        String result = "";
        result += ChatColor.GOLD + " " + anchor_name;
        result += ChatColor.RED + " " + Math.round(x*100.0)/100.0;
        result += ChatColor.GREEN + " " + Math.round(y*100.0)/100.0;
        result += ChatColor.BLUE + " " + Math.round(z*100.0)/100.0;
        return result;
    }
    public void to(Player player) {
        if(gravity) player.teleport(getHighestBlockAt().safe().getLocation());
        else player.teleport(this.getLocation());
        player.playSound(player.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.HOSTILE, 1.0f, 0.2f);
        player.sendTitle(ChatColor.GOLD + anchor_name, ChatColor.MAGIC + "_________", 10, 10, 10);
    }
    public void delayTo(Player player) {
        Location location = player.getLocation();
        for(int i=0;i<wait_time;i++) {
            int finalI = i;
            doLater(i*20L,()->counter(location,player,wait_time- finalI));
        }
        doLater(wait_time * 20L, () -> to(player));
    }
    public void counter(Location ori_location,Player player,int count_down) {
        if(ori_location.equals(player.getLocation())) {
            player.sendTitle("" + count_down, ChatColor.MAGIC + "_____",10,10,10);
            player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER, SoundCategory.MASTER,0.4f,1.0f);
        }
    }
    public Anchor getHighestBlockAt() {
        Location finalPlace = getLocation();
        int maxHeight = Objects.requireNonNull(Bukkit.getServer().getWorld(world)).getMaxHeight();
        for(int i = (int) Math.round(Math.ceil(y)); i>-64; i--) {
            if(new Anchor("temp",finalPlace.add(0,-0.5,0)).safe()!=null) {
                finalPlace = finalPlace.add(0,0.5,0);
            }
            else {
                this.setLocation(finalPlace.add(0,1,0));
                return this;
            }
        }
        return null;
    }
    public Anchor safe() {
        Location target = getLocation();
        Block body_up = Objects.requireNonNull(target.getWorld()).getBlockAt((int) x, (int) (y+1), (int) z);
        Block body_down = target.getWorld().getBlockAt((int) x, (int) (y), (int) z);
        Block below = target.getWorld().getBlockAt((int) x, (int) (y-1), (int) z);
        if(!(below.getType().isSolid() || !body_up.isEmpty() || !body_down.isEmpty())) return this;
        else {
            Objects.requireNonNull(Bukkit.getPlayer(owner)).sendMessage(ChatColor.RED + "Destination is not safe at all,thus we cancel teleport");
            return null;
        }
    }
    public void setLocation(Location location){
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.world = Objects.requireNonNull(location.getWorld()).getName();
    }
    public Location getLocation() {
        return new Location(Bukkit.getWorld(world),x,y,z,yaw,pitch);
    }
    public String add() {
        config.set(anchor_name+".owner",owner);
        config.set(anchor_name+".uuid",uuid);
        config.set(anchor_name+".gravity",gravity);
        config.set(anchor_name+".purview",purview);
        config.set(anchor_name+".wait_time",wait_time);
        config.set(anchor_name+".world", world);
        config.set(anchor_name+".X",x);
        config.set(anchor_name+".Y",y);
        config.set(anchor_name+".Z",z);
        config.set(anchor_name+".yaw",yaw);
        config.set(anchor_name+".pitch",pitch);
        saveFile();
        return "successfully set a point";
    }
    public String del() {
        config.set(anchor_name,null);
        saveFile();
        return "del successfully";
    }
    public Anchor get(String name) {
        anchor_name = name;
        owner = (String) config.get(name+".owner",owner);
        uuid = (String) config.get(name+".uuid",uuid);
        gravity =(Boolean) config.get(name+".gravity",gravity);
        purview = (String) config.get(name+".purview",purview);
        wait_time = (Integer) config.get(name+".wait_time",wait_time);
        world = (String) config.get(name+".world", world);
        x = (Double) config.get(name+".X",x);
        y = (Double) config.get(name+".Y",y);
        z = (Double) config.get(name+".Z",z);
        yaw = Float.parseFloat(String.valueOf(config.get(name+".yaw",yaw)));
        pitch = Float.parseFloat(String.valueOf(config.get(name+".pitch",pitch)));
        return this;
    }
    public static List<String> getOwnedAnchorNameList(Player player) {
        List<String> allAnchorName = getAnchorNameList();
        List<String> result = new ArrayList<>();
        String uuid = player.getUniqueId().toString();
        for (String s : allAnchorName) {
            if (uuid.equals((new Anchor().get(s)).uuid)) {
                result.add(s);
            }
        }
        return result;
    }
    public static List<String> getPublicAnchorNameList() {
        List<String> allAnchorName = getAnchorNameList();
        List<String> result = new ArrayList<>();
        for (String s : allAnchorName) {
            Anchor temp = new Anchor().get(s);
            if (temp.purview.equalsIgnoreCase("public")) {
                result.add(temp.anchor_name);
            }
        }
        return result;
    }
    public static List<String> getAnchorNameList() {
        String[] keys = Objects.requireNonNull(config).getKeys(false).toArray(new String[config.getKeys(false).size()]);
        return new ArrayList<>(Arrays.asList(keys));
    }
    public Anchor getAnchor(String name, Player player) {
        Anchor anchor = new Anchor();
        anchor.get(name);
        return this;
    }
    /*=====================================================================================*/
}
