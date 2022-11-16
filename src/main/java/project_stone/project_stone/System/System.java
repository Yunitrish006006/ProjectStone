package project_stone.project_stone.System;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import project_stone.project_stone.API.TextAPI;
import project_stone.project_stone.API.StatusAPI;
import project_stone.project_stone.Project_stone;
import project_stone.project_stone.VoidTech.tools.voidBook.VoidBook;
import project_stone.project_stone.VoidTech.tools.void_wand.VoidWand;

import java.util.List;
import java.util.Objects;

import static project_stone.project_stone.API.Config.*;

public class System implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            switch (args[0]) {
                case "Memory":{
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
                case "version":{
                    player.sendMessage("Current version:" + Project_stone.version);
                    return true;
                }
                case "give":{
                    if(match(args[1],new String[]{"void_wand"})) {
                        player.getInventory().addItem(new VoidWand().getItemStack());
                        return true;
                    }
                    else  {
                        player.getInventory().addItem(new VoidBook().getItemStack());
                        return true;
                    }
                }
                case "World":{
                    switch (args[1]) {
                        case "toSpawn":{
                            player.teleport(Objects.requireNonNull(Bukkit.getServer().getWorld(args[1])).getSpawnLocation());
                            return true;
                        }
                        case "del":{
                            return true;
                        }
                        case "add":{
                            WorldManager.createWorld(args[2]);
                            return true;
                        }
                    }
                }
                case "playerData":{
                    switch (args[1]) {
                        case "get":{
                            List<String> temp = StatusAPI.getPlayerNBT(player);
                            for(String s : temp) {
                                TextAPI.sendTest(s);
                            }
                            return true;
                        }
                        case "set":{
                            StatusAPI.setPlayerNBT(player,args[2],args[3]);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
