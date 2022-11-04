package project_stone.project_stone.PlayerChats.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import project_stone.project_stone.PlayerChats.ChatSet;
import project_stone.project_stone.PlayerChats.ChatSetDataType;
import project_stone.project_stone.PlayerChats.Methods;

import java.util.Objects;

public class SetChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            PersistentDataContainer container = player.getPersistentDataContainer();
            ChatSet chatSet;
            if(container.has(Methods.ChatSet(),new ChatSetDataType())) {
                chatSet = container.get(Methods.ChatSet(),new ChatSetDataType());
            }
            else {
                chatSet = new ChatSet(player);
            }
            if(args.length == 2) {
                if (Objects.equals(args[0], "name_color")) {
                    Objects.requireNonNull(chatSet).setName_color(args[1]);
                }
                else if (Objects.equals(args[0], "content_color")) {
                    Objects.requireNonNull(chatSet).setContent_color(args[1]);
                }
                else if (Objects.equals(args[0], "custom_name")) {
                    Objects.requireNonNull(chatSet).setCustom_name(args[1]);
                }
                else {
                    player.sendMessage(ChatColor.DARK_RED + "only <name_color/content_color/custom_name> is available");
                }
                player.getPersistentDataContainer().set(Methods.ChatSet(),new ChatSetDataType(), Objects.requireNonNull(chatSet));
                return true;
            }
            else if(args.length == 1){
                if (args[0].equalsIgnoreCase("get")) {
                    player.sendMessage("Name Color: " + Objects.requireNonNull(chatSet).getName_color() + "█");
                    player.sendMessage("Content Color: " + chatSet.getContent_color() + "█");
                    player.sendMessage("Custom Name: " + chatSet.getCustom_name());
                    return true;
                }
                else if(args[0].equalsIgnoreCase("reset")){
                    chatSet = new ChatSet(player);
                    player.getPersistentDataContainer().set(Methods.ChatSet(),new ChatSetDataType(), Objects.requireNonNull(chatSet));
                    return true;
                }
            }
        }
        return false;
    }
}
