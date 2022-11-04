package project_stone.project_stone.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class light implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,1000000,255,false,false,false));
            else player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            return true;
        }
        return false;
    }
}
