package project_stone.project_stone.events;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

import java.util.Arrays;
import java.util.List;

import static project_stone.project_stone.BasicStone.fishes;

public class OnPlayerFished implements Listener {
    @EventHandler
    public void OnHappened(ItemSpawnEvent event) {
        Item item = event.getEntity();
        if(Arrays.asList(fishes()).contains(item.getItemStack())) {
            List<Entity> entityList = item.getNearbyEntities(0.1,0.1,0.1);
            Location location = null;
            for (Entity entity : entityList) {
                if (entity.getType() == EntityType.FISHING_HOOK) {
                    location = entity.getLocation();
                }
            }
            if(location!=null) {
                switch (item.getItemStack().getType()) {
                    case COD:
                        Cod cod = (Cod) event.getEntity().getWorld().spawnEntity(event.getLocation().add(0,1,0), EntityType.COD,true);
                        cod.setVelocity(event.getEntity().getVelocity().multiply(1.6));
                        cod.setHealth(0.5);
                        cod.setRemainingAir(1);
                        cod.setSwimming(false);
                        event.setCancelled(true);
                        break;
                    case SALMON:
                        Salmon salmon = (Salmon) event.getEntity().getWorld().spawnEntity(event.getLocation().add(0,1,0), EntityType.SALMON,true);
                        salmon.setVelocity(event.getEntity().getVelocity().multiply(1.6));
                        salmon.setHealth(0.5);
                        salmon.setRemainingAir(1);
                        salmon.setSwimming(false);
                        event.setCancelled(true);
                        break;
                    case PUFFERFISH:
                        PufferFish pufferFish = (PufferFish) event.getEntity().getWorld().spawnEntity(event.getLocation().add(0,1,0), EntityType.PUFFERFISH,true);
                        pufferFish.setVelocity(event.getEntity().getVelocity().multiply(1.6));
                        pufferFish.setHealth(0.5);
                        pufferFish.setRemainingAir(1);
                        pufferFish.setSwimming(false);
                        event.setCancelled(true);
                        break;
                    case TROPICAL_FISH:
                        TropicalFish TropicalFish = (TropicalFish) event.getEntity().getWorld().spawnEntity(event.getLocation().add(0,1,0), EntityType.TROPICAL_FISH,true);
                        TropicalFish.setVelocity(event.getEntity().getVelocity().multiply(1.6));
                        TropicalFish.setHealth(1.5);
                        TropicalFish.setRemainingAir(1);
                        TropicalFish.setSwimming(false);
                        event.setCancelled(true);
                        break;
                    default:
                        event.getEntity().setVelocity(event.getEntity().getVelocity());
                        break;
                }
            }
        }
    }
}
