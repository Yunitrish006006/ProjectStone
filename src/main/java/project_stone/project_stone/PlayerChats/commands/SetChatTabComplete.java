package project_stone.project_stone.PlayerChats.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SetChatTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add("name_color");
            options.add("content_color");
            options.add("custom_name");
            options.add("reset");
            options.add("get");
            return options;
        }
        else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("name_color") || args[0].equalsIgnoreCase("content_color")) {
                List<String> options = new ArrayList<>();
                options.add("aqua");
                options.add("black");
                options.add("blue");
                options.add("bold");
                options.add("dark_aqua");
                options.add("dark_blue");
                options.add("dark_gray");
                options.add("dark_green");
                options.add("dark_purple");
                options.add("dark_red");
                options.add("gold");
                options.add("gray");
                options.add("green");
                options.add("italic");
                options.add("light_purple");
                options.add("magic");
                options.add("red");
                options.add("reset");
                options.add("strikethrough");
                options.add("underline");
                options.add("white");
                options.add("yellow");
                return options;
            }
            else if(args[0].equalsIgnoreCase("custom_name")) {
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }
}
