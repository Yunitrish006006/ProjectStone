package project_stone.project_stone.VoidTech;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import project_stone.project_stone.BasicStone;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class AnchorFinder implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            switch (args.length){
                case 1:
                    List<String> options = new ArrayList<>();
                    options.add("to");
                    options.add("player");
                    options.add("add");
                    options.add("death");
                    options.add("list");
                    options.add("set");
                    options.add("del");
                    options.add(ChatColor.MAGIC+"pet");
                    options.add(ChatColor.MAGIC+"tag");
                    return options;
                case 2:
                    if(BasicStone.same(args[0],"to")){
                        return Anchor.getOwnedAnchorNameList(player);
                    }
                    else if(BasicStone.same(args[0],"set")){
                        return Anchor.getOwnedAnchorNameList(player);
                    }
                    else if(BasicStone.same(args[0],"del")){
                        return Anchor.getOwnedAnchorNameList(player);
                    }
                    else if(BasicStone.same(args[0],"player")) {
                        return BasicStone.getPlayerList();
                    }
                    else if(BasicStone.same(args[0],"add")) {
                        StringBuilder x = new StringBuilder();
                        for(int i=0;i<4;i++) {
                            x.append((char) ('a' + (Math.random() * 26)));
                        }
                        List<String> random_id = new ArrayList<>();
                        random_id.add(x.toString());
                        return random_id;
                    }
            }
        }
        return new ArrayList<>();
    }
}
