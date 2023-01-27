package project_stone.project_stone.VoidTech.tools.voidBook;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import project_stone.project_stone.API.TextAPI;
import project_stone.project_stone.VoidTech.Anchor;

import java.util.Objects;

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
        ItemStack itemStack = Objects.requireNonNull(event.getCurrentItem()).clone();

        if(event.getClickedInventory().getType().equals(InventoryType.ANVIL)) {
            ItemStack[] x = event.getClickedInventory().getContents();
            for(ItemStack s : x) {
                TextAPI.sendTest(s.toString());
            }
        }
        if(Objects.requireNonNull(itemStack.getItemMeta()).getLocalizedName().split(",")[0].equalsIgnoreCase("ProjectStone:anchor")) {
            if(event.getClick().equals(ClickType.LEFT)) {
                if(itemStack.getItemMeta().getLocalizedName().split(",")[1].equalsIgnoreCase("unit")) {
                    Anchor anchor = new Anchor().get(itemStack.getItemMeta().getLocalizedName().split(",")[2]);
                    anchor.canTeleport(player).delayTo(player);
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
                else if(itemStack.getItemMeta().getLocalizedName().split(",")[1].equalsIgnoreCase("modify_unit")) {
                    Anchor anchor = new Anchor().get(itemStack.getItemMeta().getLocalizedName().split(",")[2]);
                    if(event.getClickedInventory().getType().equals(InventoryType.ANVIL)) {
                        ItemStack[] temps = event.getClickedInventory().getContents();
                        for(ItemStack s : temps) {
                            TextAPI.sendTest(s.getItemMeta().getLocalizedName());
                        }
                    }
                    player.closeInventory();
                }
            }
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack result = new ItemStack(Material.STRING);
        event.setResult(result);
        event.getInventory().setRepairCost(0);
    }
}
