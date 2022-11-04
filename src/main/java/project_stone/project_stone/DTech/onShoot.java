package project_stone.project_stone.DTech;

import org.bukkit.Bukkit;
import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.Inventory;

public class onShoot implements Listener {
    @EventHandler
    public void shootFromBlock(BlockDispenseEvent event) {
        if(event.getBlock() instanceof Dispenser) {
            Dispenser dispenser = (Dispenser) event.getBlock();
            Inventory inventory = dispenser.getInventory();
            Bukkit.getServer().broadcastMessage(inventory.toString());
        }
    }
}
