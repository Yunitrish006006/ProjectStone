package project_stone.project_stone.API;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TextAPI {
    public static void sendActionBar(Player player,String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(message));
    }
    public static void sendTest(String message) {
        for(Player player: Bukkit.getServer().getOnlinePlayers()) {
            if (player.isOp()) {
                player.spigot().sendMessage(ChatMessageType.CHAT,new TextComponent(message));
            }
        }
    }
    public static String keyAndValueText(String key,String value) {
        return ChatColor.RESET + "" + ChatColor.GRAY + key + " : " + ChatColor.GREEN + value;
    }
    public static String toRawText(String value) {
        for(ChatColor colors : ChatColor.values()) {
            value = value.replace((CharSequence) colors,"");
            sendTest(colors+"*");
        }
        return value;
    }
}
