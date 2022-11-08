package project_stone.project_stone.System;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static project_stone.project_stone.API.Config.match;

public class SystemTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            switch (args.length) {
                case 1: {
                    List<String> options = new ArrayList<>();
                    options.add("memory");
                    options.add("give");
                    return options;
                }
                case 2: {
                    if(match(args[0],"give")) {
                        List<String> options = new ArrayList<>();
                        options.add("void_wand");
                        return options;
                    }
                }
            }
        }
        return new ArrayList<>();
    }
}
