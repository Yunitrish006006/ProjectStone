package project_stone.project_stone.VoidSystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import project_stone.project_stone.BasicStone;

import java.util.ArrayList;
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
                    options.add("set");
                    options.add("remove");
                    options.add("death");
                    options.add("list");
                    options.add("player");
                    return options;
                case 2:
                    if(BasicStone.same(args[0],"to")) {
                        return  void_anchor.getAnchors(player);
                    }
                    else if(BasicStone.same(args[0],"player")) {
                        return BasicStone.playerNames();
                    }
            }
        }
        return new ArrayList<>();
    }
}
