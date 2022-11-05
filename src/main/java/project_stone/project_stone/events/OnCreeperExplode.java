package project_stone.project_stone.events;

import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnCreeperExplode implements Listener {
    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {
        if(event.getEntity() instanceof Creeper) {
            Creeper creeper = (Creeper) event.getEntity();
            Location c_location = creeper.getLocation();
            List<Entity> nearbyEntities = new ArrayList<>(Objects.requireNonNull(c_location.getWorld()).getNearbyEntities(c_location, 4.0, 4.0, 4.0));
            for (Entity nearbyEntity : nearbyEntities) {
                Location ori = nearbyEntity.getLocation();
                Vector vector = new Vector();
                vector.setX(ori.getX() - c_location.getX());
                vector.setY((ori.getY() - c_location.getY())+2);
                vector.setZ(ori.getZ() - c_location.getZ());
                nearbyEntity.setVelocity(vector.multiply(3));
            }
            event.setCancelled(true);
        }
    }
}
