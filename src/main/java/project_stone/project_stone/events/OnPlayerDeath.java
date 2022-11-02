package project_stone.project_stone.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import project_stone.project_stone.VoidSystem.void_anchor;

public class OnPlayerDeath implements Listener {
    @EventHandler
    public void RecordDeathPoint(PlayerDeathEvent event) {
        Player player = event.getEntity();
        void_anchor.addAnchor(player,"Death");
        player.sendMessage(ChatColor.GOLD + "Death point record at" + "\n" + void_anchor.LocationString(player));
    }
}
