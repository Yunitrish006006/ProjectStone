package project_stone.project_stone.VoidTech;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import project_stone.project_stone.BasicStone;
import project_stone.project_stone.Project_stone;

import java.util.List;
import java.util.Objects;

import static project_stone.project_stone.VoidTech.Anchor.*;

public class void_anchor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            switch (args.length){
                case 0:
                    player.sendMessage(ChatColor.GOLD + "help:");
                    player.sendMessage("  " + Project_stone.getPlugin().getName());
                    player.sendMessage("  " + Project_stone.getRootFolder());
                    return true;
                case 1:
                    if(BasicStone.same(args[0],"list")) {
                        List<String> points = getOwnedAnchorNameList(player);
                        for(int i=0;i<getOwnedAnchorNameList(player).size();i++) {
                            player.sendMessage(new Anchor().get(points.get(i)).getLocationInformation());
                        }
                        return true;
                    }
                case 2:
                    if(BasicStone.same(args[0],"to")) {
                        new Anchor().get(args[1]).delayTo(player);
                        return true;
                    }
                    else if(BasicStone.same(args[0],"player")) {
                        new Anchor("temp", Objects.requireNonNull(Bukkit.getPlayer(args[1]))).delayTo(player);
                        return true;
                    }
                    else if(BasicStone.same(args[0],"add")) {
                        player.sendMessage(new Anchor(args[1],player).add());
                        return true;
                    }
                    else if(BasicStone.same(args[0],"del")) {
                        player.sendMessage(new Anchor().get(args[1]).del());
                        return true;
                    }
            }
        }
        return false;
    }
}
