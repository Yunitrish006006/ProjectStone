package project_stone.project_stone.VoidTech.tools.void_wand;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class VoidWand {
    private Location from;
    private Location to;
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private List<String> lore;
    public VoidWand() {
        itemStack = new ItemStack(Material.STICK);
        itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("Void Wand");
        itemMeta.setCustomModelData(1);
        assert lore != null;
        lore = new ArrayList<>();
        lore.add("a magic wand that can transfer a big amount of blocks!");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }
    public ItemMeta getItemMeta() {
        return itemMeta;
    }
    public List<String> getLore() {
        return lore;
    }
    public Location getFromPoint() {
        return from;
    }
    public void setFromPoint(Location location) {
        from = location;
    }
    public void setToPoint(Location location) {
        to = location;
    }
    public Location getToPoint() {
        return to;
    }
    public ItemStack getItemStack() {
        return itemStack;
    }
}
