package project_stone.project_stone.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class hat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack helmet = player.getInventory().getItemInMainHand();
            ItemStack mainHand = player.getInventory().getHelmet();
            player.getInventory().setHelmet(helmet);
            player.getInventory().setItemInMainHand(mainHand);
            return true;
        }
        return false;
    }
}
