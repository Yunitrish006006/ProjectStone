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
                    options.add("version");
                    options.add("PlayerData");
                    options.add("world");
                    return options;
                }
                case 2: {
                    if(match(args[0],"give")) {
                        List<String> options = new ArrayList<>();
                        options.add("void_wand");
                        options.add("void_book");
                        return options;
                    }
                    else if (match(args[0],"PlayerData")) {
                        List<String> options = new ArrayList<>();
                        options.add("set");
                        options.add("get");
                        return options;
                    }
                    else if (match(args[0],"World")) {
                        List<String> options = new ArrayList<>();
                        options.add("add");
                        options.add("toSpawn");
                        options.add("del");
                        return options;
                    }
                }
                case 3: {
                    if (match(args[0],"World")) {
                        switch (args[1]) {
                            case "add": {
                                StringBuilder x = new StringBuilder();
                                for(int i=0;i<16;i++) {
                                    x.append((char) ('a' + (Math.random() * 26)));
                                }
                                List<String> random_id = new ArrayList<>();
                                random_id.add(x.toString());
                                return random_id;
                            }
                            case "del":
                            case "toSpawn": {
                                return WorldManager.getWorldList();
                            }
                        }

                    }
                }
            }
        }
        return new ArrayList<>();
    }
}
