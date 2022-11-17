package project_stone.project_stone.VoidTech.tools.voidBook;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import project_stone.project_stone.VoidTech.Anchor;

public class OnUsingBook implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        VoidBook voidBook = new VoidBook();
        if(voidBook.getItemStack().equals(event.getItem())) {
            player.openInventory(voidBook.GUI_hub(player));
        }
    }
    @EventHandler
    public void onGUIClicked(InventoryClickEvent event) {
        VoidBook voidBook = new VoidBook();
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem().clone();
        if(itemStack.getItemMeta().getLocalizedName().split(",")[0].equalsIgnoreCase("ProjectStone:anchor")) {
            if(event.getClick().equals(ClickType.LEFT)) {
                if(itemStack.getItemMeta().getLocalizedName().split(",")[1].equalsIgnoreCase("unit")) {
                    Anchor anchor = new Anchor().get(itemStack.getItemMeta().getLocalizedName().split(",")[2]);
                    anchor.canTeleport(player).delayTo(player);
                    player.closeInventory();
                }
                else if(itemStack.getItemMeta().getLocalizedName().split(",")[1].equalsIgnoreCase("modify_unit")) {
                    Anchor anchor = new Anchor().get(itemStack.getItemMeta().getLocalizedName().split(",")[2]);
                    anchor.setAnchor_name(itemStack.getItemMeta().getDisplayName());
                    player.closeInventory();
                }
                else if(itemStack.getItemMeta().getLocalizedName().split(",")[1].equalsIgnoreCase("add_unit")) {
                    Anchor anchor = new Anchor("temp",player);
                    anchor.setLocation(player.getLocation());
                    anchor.add();
                    player.closeInventory();
                }
            } else if (event.getClick().equals(ClickType.RIGHT)) {
                if(itemStack.getItemMeta().getLocalizedName().split(",")[1].equalsIgnoreCase("unit")) {
                    Anchor anchor = new Anchor().get(itemStack.getItemMeta().getLocalizedName().split(",")[2]);
                    player.openInventory(voidBook.GUI_set(anchor));
                }
                else if(itemStack.getItemMeta().getLocalizedName().split(",")[1].equalsIgnoreCase("set_name")) {
                    Anchor anchor = new Anchor().get(itemStack.getItemMeta().getLocalizedName().split(",")[2]);
                    player.openInventory(voidBook.GUI_set_name(anchor));
                }
                else if(itemStack.getItemMeta().getLocalizedName().split(",")[1].equalsIgnoreCase("delete")) {
                    Anchor anchor = new Anchor().get(itemStack.getItemMeta().getLocalizedName().split(",")[2]);
                    anchor.del();
                    player.closeInventory();
                }
            }
            event.setCancelled(true);
        }
    }
}
