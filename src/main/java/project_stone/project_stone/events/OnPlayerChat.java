package project_stone.project_stone.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import project_stone.project_stone.API.DiscordWebhook;
import project_stone.project_stone.Project_stone;

import java.awt.*;

public class OnPlayerChat implements Listener {
    private String webhook_url = "https://discord.com/api/webhooks/1068513559446835301/rk4Tpe_Wy0JTbbiAzWVXqZsDRH3QpoMWBqx67fw28V7ZmhGtpVbGW7m6pqOQxEuAt70Z";
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        DiscordWebhook webhook = new DiscordWebhook(webhook_url);
        message = message.replaceAll("@everyone","`@everyone`").replaceAll("@here","`@here`");
        webhook.setContent("**"+player.getName()+"**"+" : "+message);
        try {
            webhook.execute();
        }
        catch (java.io.IOException e){
            Project_stone.getPlugin().getLogger().severe(e.getStackTrace().toString());
        }
    }
}
