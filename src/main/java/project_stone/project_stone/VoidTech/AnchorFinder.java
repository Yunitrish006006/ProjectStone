package project_stone.project_stone.VoidTech;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static project_stone.project_stone.API.Config.getPlayerList;
import static project_stone.project_stone.API.Config.match;

public class AnchorFinder implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            switch (args.length){
                case 1:
                    List<String> options = new ArrayList<>();
                    options.add("to");
                    options.add("public");
                    options.add("player");
                    options.add("add");
                    options.add("list");
                    options.add("set");
                    options.add("del");
                    options.add(ChatColor.MAGIC+"pet");
                    options.add(ChatColor.MAGIC+"tag");
                    return options;
                case 2:
                    if(match(args[0],"to")){
                        return Anchor.getOwnedAnchorNameList(player);
                    }
                    else if(match(args[0],"set")){
                        return Anchor.getOwnedAnchorNameList(player);
                    }
                    else if(match(args[0],"public")) {
                        return Anchor.getPublicAnchorNameList();
                    }
                    else if(match(args[0],"del")){
                        return Anchor.getOwnedAnchorNameList(player);
                    }
                    else if(match(args[0],"player")) {
                        return getPlayerList();
                    }
                    else if(match(args[0],"add")) {
                        StringBuilder x = new StringBuilder();
                        for(int i=0;i<4;i++) {
                            x.append((char) ('a' + (Math.random() * 26)));
                        }
                        List<String> random_id = new ArrayList<>();
                        random_id.add(x.toString());
                        return random_id;
                    }
                case 3: {
                    if(match(args[0],"set")){
                        if(match(args[1],Anchor.getOwnedAnchorNameList(player).toArray(new String[0]))){
                            List<String> temp = new ArrayList<>();
                            temp.add("gravity");
                            temp.add("purview");
                            temp.add("wait_time");
                            return temp;
                        }
                    }
                }
                case 4: {
                    if(match(args[0],"set")){
                        if(match(args[2],"gravity")){
                            List<String> t3 = new ArrayList<>();
                            t3.add("true");
                            t3.add("false");
                            return t3;
                        }
                        else if(match(args[2],"purview")){
                            List<String> t3 = new ArrayList<String>(){};
                            t3.add("public");
                            t3.add("private");
                            return t3;
                        }
                        else if(match(args[2],"wait_time")){
                            List<String> t3 = new ArrayList<String>(){};
                            t3.add("1");
                            t3.add("2");
                            t3.add("3");
                            return t3;
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }
}
