package project_stone.project_stone.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import project_stone.project_stone.API.DiscordWebhook;
import project_stone.project_stone.Project_stone;

import java.awt.*;

public class OnPlayerPortal implements Listener {
    private String webhook_url = "https://discord.com/api/webhooks/1068513559446835301/rk4Tpe_Wy0JTbbiAzWVXqZsDRH3QpoMWBqx67fw28V7ZmhGtpVbGW7m6pqOQxEuAt70Z";
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        int playerCount = Bukkit.getOnlinePlayers().size();
        DiscordWebhook webhook = new DiscordWebhook(webhook_url);
        Player player = event.getPlayer();
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription(
                "**" + player.getPlayer().getName() + "** has joined the server!" +
                playerCount + "/" + Bukkit.getMaxPlayers()
        ).setAuthor(player.getName(),"","").setColor(Color.ORANGE)
        );
        try {
            webhook.execute();
        }
        catch (java.io.IOException e){
            Project_stone.getPlugin().getLogger().severe(e.getStackTrace().toString());
        }
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        int playerCount = Bukkit.getOnlinePlayers().size();
        DiscordWebhook webhook = new DiscordWebhook(webhook_url);
        Player player = event.getPlayer();
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription(
                        "**" + player.getPlayer().getName() + "** has quit the server!" +
                                (playerCount-1) + "/" + Bukkit.getMaxPlayers()
                ).setAuthor(player.getName(),"","").setColor(Color.ORANGE)
        );
        try {
            webhook.execute();
        }
        catch (java.io.IOException e){
            Project_stone.getPlugin().getLogger().severe(e.getStackTrace().toString());
        }
    }
}
