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

import static project_stone.project_stone.API.Config.*;

public class OnUsingVoidWand implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(new VoidWand().getItemStack().equals(event.getItem()))) return;
        if(!isInteractBlockWithMainHandOn(event)) return;
        if(getTriggeredFromPlayer(player,"void_wand")) return;
        else setTriggeredToPlayer(player,"void_wand");
        VoidWand voidWand = new VoidWand();
        if(event.getAction().equals(Action.LEFT_CLICK_BLOCK) && event.getPlayer().isSneaking()) {
            Location target = event.getClickedBlock().getLocation();
            savePointToPlayer(player,target,"from");
            player.sendMessage("select block from "+target.getBlockX()+", "+target.getY()+", "+target.getZ());
            event.setCancelled(true);
        }
        else if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            Location target = event.getClickedBlock().getLocation();
            Configuration config = getConfig();
            savePointToPlayer(player,target,"to");
            player.sendMessage("select block to "+target.getBlockX()+", "+target.getY()+", "+target.getZ());
            event.setCancelled(true);
        }
        else if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Location target = event.getClickedBlock().getLocation();
            Configuration config = getConfig();
            Location from = getPointFromPlayer(player,"from");
            Location to = getPointFromPlayer(player,"to");
            player.sendMessage(CloneBlocks(from,to,target));
//            BasicStone.removePointFromPlayer(player,"from");
//            BasicStone.removePointFromPlayer(player,"to");
            event.setCancelled(true);
        }
    }
    public void SetBlock(Location p1,Material material) {
        p1.getBlock().setBlockData(Bukkit.createBlockData(material),true);
    }
    public void SetBlocks(Location p1,Material material) {
        SetBlock(p1,material);
        SetBlock(p1.clone().add(1,0,0),material);
        SetBlock(p1.clone().add(0,0,1),material);
    }

    public String CloneBlocks(Location i1,Location i2,Location o1) {
        Location p1 = i1.clone();
        Location p2 = i2.clone();
        Location target = o1.clone();
        if(p2.getBlockX()-p1.getBlockX()==0 || p2.getBlockY()-p1.getBlockY()==0 || p2.getBlockZ()-p1.getBlockZ()==0) return "num error, vx,vy,vz ,can not be 0!";
        int vx = (p2.getBlockX()-p1.getBlockX())/Math.abs(p2.getBlockX()-p1.getBlockX());
        int vy = (p2.getBlockY()-p1.getBlockY())/Math.abs(p2.getBlockY()-p1.getBlockY());
        int vz = (p2.getBlockZ()-p1.getBlockZ())/Math.abs(p2.getBlockZ()-p1.getBlockZ());
        for(int i=0;i<=(p2.getBlockX()-p1.getBlockX());i+=vx) {
            for(int k=0;k<=(p2.getBlockZ()-p1.getBlockZ());k+=vz) {
                for(int j=0;j<=(p2.getBlockY()-p1.getBlockY());j+=vy) {
                    Location current = p1.clone().add(i,j,k);
                    BlockData block = current.getBlock().getBlockData();
                    if(target.clone().add(i,j,k).getBlock().isEmpty()) target.clone().add(i,j,k).getBlock().setBlockData(block);
                    else {
                        target.clone().getBlock().breakNaturally();
                        target.clone().add(i,j,k).getBlock().setBlockData(block);
                    }
//                    current.getBlock().setBlockData(Bukkit.createBlockData(Material.AIR),true);
                }
            }
        }
        return "copy from block\n" + getLocationXYZ(p1) + "-" + getLocationXYZ(p2) +
                "\nto\n" + getLocationXYZ(target.clone()) + "-" +
                getLocationXYZ(target.clone().add(p2.getBlockX()-p1.getBlockX(),p2.getBlockY()-p1.getBlockY(),p2.getBlockZ()-p1.getBlockZ())) +
                "\nwith direction " + vx+","+vy+","+vz;

    }
}
