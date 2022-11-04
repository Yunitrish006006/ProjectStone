package project_stone.project_stone.PlayerChats.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import project_stone.project_stone.PlayerChats.ChatSet;
import project_stone.project_stone.PlayerChats.ChatSetDataType;
import project_stone.project_stone.PlayerChats.Methods;

import java.util.Objects;

public class OnPlayerChat implements Listener {
    @EventHandler
    public void OnChat(AsyncPlayerChatEvent event) {

        ChatSet chatSet = new ChatSet(event.getPlayer());

        if(event.getPlayer().getPersistentDataContainer().has(Methods.ChatSet(),new ChatSetDataType())){
            chatSet = event.getPlayer().getPersistentDataContainer().get(Methods.ChatSet(),new ChatSetDataType());
        }
        Bukkit.getServer().broadcastMessage(Objects.requireNonNull(chatSet).getName_color() + chatSet.getCustom_name() + ChatColor.RESET + " " +chatSet.getContent_color() + event.getMessage());
        event.setCancelled(true);
    }
}
