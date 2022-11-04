package project_stone.project_stone.PlayerChats;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Locale;

public class ChatSet implements Serializable {
    private static final long serialVersionUID = 1L;
    private ChatColor name_color;
    private ChatColor content_color;
    private String custom_name;

    public ChatSet(Player player) {
        this.custom_name = player.getDisplayName();
        this.name_color = ChatColor.WHITE;
        this.content_color = ChatColor.WHITE;
    }
    public ChatColor getContent_color(){return content_color;}
    public ChatColor getName_color(){return name_color;}
    public String getCustom_name(){return custom_name;}
    public void setContent_color(ChatColor color){this.content_color = color;}
    @Nullable
    public void setContent_color(String color){
        switch (color.toLowerCase(Locale.ROOT)){
            case "aqua":
                setContent_color(ChatColor.AQUA);
                break;
            case "black":
                setContent_color(ChatColor.BLACK);
                break;
            case "blue":
                setContent_color(ChatColor.BLUE);
                break;
            case "bold":
                setContent_color(ChatColor.BOLD);
                break;
            case "dark_aqua":
                setContent_color(ChatColor.DARK_AQUA);
                break;
            case "dark_blue":
                setContent_color(ChatColor.DARK_BLUE);
                break;
            case "dark_gray":
                setContent_color(ChatColor.DARK_GRAY);
                break;
            case "dark_green":
                setContent_color(ChatColor.DARK_GREEN);
                break;
            case "dark_purple":
                setContent_color(ChatColor.DARK_PURPLE);
                break;
            case "dark_red":
                setContent_color(ChatColor.DARK_RED);
                break;
            case "gold":
                setContent_color(ChatColor.GOLD);
                break;
            case "gray":
                setContent_color(ChatColor.GRAY);
                break;
            case "green":
                setContent_color(ChatColor.GREEN);
                break;
            case "italic":
                setContent_color(ChatColor.ITALIC);
                break;
            case "light_purple":
                setContent_color(ChatColor.LIGHT_PURPLE);
                break;
            case "magic":
                setContent_color(ChatColor.MAGIC);
                break;
            case "red":
                setContent_color(ChatColor.RED);
                break;
            case "reset":
                setContent_color(ChatColor.RESET);
                break;
            case "strikethrough":
                setContent_color(ChatColor.STRIKETHROUGH);
                break;
            case "underline":
                setContent_color(ChatColor.UNDERLINE);
                break;
            case "white":
                setContent_color(ChatColor.WHITE);
                break;
            case "yellow":
                setContent_color(ChatColor.YELLOW);
                break;
            default:
                setContent_color(ChatColor.WHITE);
                break;
        }
    }
    public void setName_color(ChatColor color){this.name_color = color;}
    @Nullable
    public void setName_color(String color){
        switch (color.toLowerCase(Locale.ROOT)){
            case "aqua":
                setName_color(ChatColor.AQUA);
                break;
            case "black":
                setName_color(ChatColor.BLACK);
                break;
            case "blue":
                setName_color(ChatColor.BLUE);
                break;
            case "bold":
                setName_color(ChatColor.BOLD);
                break;
            case "dark_aqua":
                setName_color(ChatColor.DARK_AQUA);
                break;
            case "dark_blue":
                setName_color(ChatColor.DARK_BLUE);
                break;
            case "dark_gray":
                setName_color(ChatColor.DARK_GRAY);
                break;
            case "dark_green":
                setName_color(ChatColor.DARK_GREEN);
                break;
            case "dark_purple":
                setName_color(ChatColor.DARK_PURPLE);
                break;
            case "dark_red":
                setName_color(ChatColor.DARK_RED);
                break;
            case "gold":
                setName_color(ChatColor.GOLD);
                break;
            case "gray":
                setName_color(ChatColor.GRAY);
                break;
            case "green":
                setName_color(ChatColor.GREEN);
                break;
            case "italic":
                setName_color(ChatColor.ITALIC);
                break;
            case "light_purple":
                setName_color(ChatColor.LIGHT_PURPLE);
                break;
            case "magic":
                setName_color(ChatColor.MAGIC);
                break;
            case "red":
                setName_color(ChatColor.RED);
                break;
            case "reset":
                setName_color(ChatColor.RESET);
                break;
            case "strikethrough":
                setName_color(ChatColor.STRIKETHROUGH);
                break;
            case "underline":
                setName_color(ChatColor.UNDERLINE);
                break;
            case "white":
                setName_color(ChatColor.WHITE);
                break;
            case "yellow":
                setName_color(ChatColor.YELLOW);
                break;
            default:
                setName_color(ChatColor.WHITE);
                break;
        }
    }
    public void setCustom_name(String name){this.custom_name = name;}

}

