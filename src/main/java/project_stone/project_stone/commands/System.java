package project_stone.project_stone.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class System implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("Memory")) {
                    int use = Integer.parseInt(String.valueOf(Runtime.getRuntime().totalMemory()/(1024*1024))) -Integer.parseInt(String.valueOf(Runtime.getRuntime().freeMemory()/(1024*1024)));
                    int all = Integer.parseInt(String.valueOf(Runtime.getRuntime().maxMemory()/(1024*1024)));
                    player.sendMessage(ChatColor.GOLD+"("+ChatColor.BLUE+ use + ChatColor.GOLD + "/" + all +")MB");
                    player.sendMessage(ChatColor.GOLD+"("+ChatColor.BLUE+
                            Math.round(((float)(use)/1024)*10.0)/10.0
                            +ChatColor.GOLD+"/"+
                            Math.round(((float)(all)/1024)*10.0)/10.0
                            +")GB");
                    return true;
                }
            }
        }
        return false;
    }
}
