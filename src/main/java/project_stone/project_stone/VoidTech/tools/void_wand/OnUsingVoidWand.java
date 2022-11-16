package project_stone.project_stone.VoidTech.tools.void_wand;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;
import project_stone.project_stone.API.Config;

import static project_stone.project_stone.API.Config.*;
import static project_stone.project_stone.API.MessageAPI.sendActionBar;

public class OnUsingVoidWand implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!Config.matchItemValue(event.getItem(),new VoidWand().getItemStack())) return;
        if(!isInteractBlockWithMainHandOn(event)) return;
        //test if using
        if(getTriggeredFromPlayer(player,"void_wand")) return;
        VoidWand voidWand = new VoidWand();
        if(event.getAction().equals(Action.LEFT_CLICK_BLOCK) && event.getPlayer().isSneaking()) {
            Location target = event.getClickedBlock().getLocation();
            savePointToPlayer(player,target,"from");
            sendActionBar(player,"select block from "+target.getBlockX()+", "+target.getBlockY()+", "+target.getBlockZ());
            event.setCancelled(true);
        }
        else if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            Location target = event.getClickedBlock().getLocation();
            savePointToPlayer(player,target,"to");
            sendActionBar(player,"select block to "+target.getBlockX()+", "+target.getBlockY()+", "+target.getBlockZ());
            event.setCancelled(true);
        }
        else if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && EquipmentSlot.HAND.equals(event.getHand())) {
            Location target = event.getClickedBlock().getLocation();
            Configuration config = getConfig();
            Location from = getPointFromPlayer(player,"from");
            Location to = getPointFromPlayer(player,"to");

            getTowardFrom(from,to);
            sendActionBar(player,CloneBlocks(from,to,target));
//            BasicStone.removePointFromPlayer(player,"from");
//            BasicStone.removePointFromPlayer(player,"to");
            setTriggeredToPlayer(player,"void_wand");
            event.setCancelled(true);
        }
    }
    public void SetBlock(Location p1,Material material) {
        p1.getBlock().setBlockData(Bukkit.createBlockData(material),true);
    }

    public Vector getTowardFrom(Location from, Location to) {
        int dx = to.getBlockX()-from.getBlockX();
        int dy = to.getBlockY()-from.getBlockY();
        int dz = to.getBlockZ()-from.getBlockZ();
        Vector vector = new Vector(dx,dy,dz).toBlockVector();
        Vector updated = new Vector().setX(dx/Math.abs(dx)).setY(dy/Math.abs(dy)).setZ(dz/Math.abs(dz));
        Bukkit.getServer().broadcastMessage("pos: "+updated.getX()+","+updated.getY()+","+updated.getZ());
        return updated;
    }

    public String CloneBlocks(Location i1,Location i2,Location o1) {
        Location p1 = i1.clone();
        Location p2 = i2.clone();
        Location target = o1.clone();
        Vector v = getTowardFrom(i1,i2);
        for(int i=0;i<=(p2.getBlockX()-p1.getBlockX());i+=v.getX()) {
            for(int k=0;k<=(p2.getBlockZ()-p1.getBlockZ());k+=v.getZ()) {
                for(int j=0;j<=(p2.getBlockY()-p1.getBlockY());j+=v.getY()) {
                    BlockData block = p1.clone().add(i,j,k).getBlock().getBlockData();
                    target.clone().getBlock().breakNaturally();
                    target.clone().add(i,j,k).getBlock().setBlockData(block);
//                    p1.clone().add(i,j,k).getBlock().setBlockData(Bukkit.createBlockData(Material.AIR),true);
                }
            }
        }
        String x = "copy from\n" + getLocationXYZ(p1) + "-" + getLocationXYZ(p2) +
                "\nto\n" + getLocationXYZ(target.clone()) + "-" +
                getLocationXYZ(target.clone().add(p2.getBlockX()-p1.getBlockX(),p2.getBlockY()-p1.getBlockY(),p2.getBlockZ()-p1.getBlockZ())) +
                "\nwith direction " + v.getX()+","+v.getY()+","+v.getZ();
        return "copied";

    }
}
