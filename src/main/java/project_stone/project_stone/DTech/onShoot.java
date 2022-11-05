package project_stone.project_stone.DTech;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class onShoot implements Listener {
    @EventHandler
    public void shootFromBlock(BlockDispenseEvent event) {
        if(event.getBlock().getType().equals(Material.DISPENSER)) {
            if(event.getItem().getType().equals(Material.STONE_PICKAXE)){
                ItemStack stack = event.getItem();
                ItemMeta meta = stack.getItemMeta();
                assert meta != null;
                Map<String, Object> metas = meta.serialize();
                metas.put("Damage",(((int)(metas.get("Damage"))+1)));
                Bukkit.getServer().broadcastMessage(String.valueOf(metas.get("Damage")));
                event.setCancelled(true);
            }
        }
    }
}
