package project_stone.project_stone.VoidTech.tools.voidBook;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import project_stone.project_stone.API.TextAPI;
import project_stone.project_stone.VoidTech.Anchor;

import java.util.Objects;


public class OnUsingBook implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        VoidBook voidBook = new VoidBook();
        if(voidBook.getItemStack().equals(event.getItem()))
            player.openInventory(voidBook.GUI_hub(player));
    }
    @EventHandler
    public void onTeleportPointClicked(InventoryClickEvent event) {
        VoidBook voidBook = new VoidBook();
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = Objects.requireNonNull(event.getCurrentItem()).clone();
        if("VoidTech:void_book".equalsIgnoreCase(itemStack.getItemMeta().getLore().get(0))) {
            Anchor anchor = new Anchor().get(itemStack.getItemMeta().getDisplayName());
            TextAPI.sendTest(anchor.getAnchor_name());
            anchor.canTeleport(player).delayTo(player);
            player.closeInventory();
            event.setCancelled(true);
        }
    }
}
