package project_stone.project_stone.VoidTech.tools.voidBook;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import project_stone.project_stone.VoidTech.Anchor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VoidBook {
    private Location from;
    private Location to;
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private List<String> lore;
    public VoidBook() {
        itemStack = new ItemStack(Material.KNOWLEDGE_BOOK);
        itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("Void Book");
        itemMeta.setCustomModelData(1);
        assert lore != null;
        lore = new ArrayList<>();
        lore.add("a book that can bring u anywhere");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    public Inventory GUI_hub(Player player) {
        Inventory main = Bukkit.createInventory(null,36,"Teleport Hub");
        main.setContents(points(player).toArray(new ItemStack[0]));
        return main;
    }

    public List<ItemStack> points(Player player) {
        List<ItemStack> points = new ArrayList<>();
        for(String a_name : Anchor.getOwnedAnchorNameList(player)) {
            Anchor anchor = new Anchor().get(a_name);
            ItemStack temp = new ItemStack(Objects.requireNonNull(Material.getMaterial(anchor.getIcon().toUpperCase(),true)));
            ItemMeta meta = temp.getItemMeta();
            List<String> lores = new ArrayList<>();
            lores.add("VoidTech:void_book");
            lores.add("purview : " + anchor.getPurview());
            lores.add("gravity : " + anchor.isGravity());
            lores.add("wait time : " + anchor.getWait_time());
            lores.add("position : " + anchor.getPureLocation());
            assert meta != null;
            meta.setLore(lores);
            meta.setDisplayName(anchor.getAnchor_name());
            temp.setItemMeta(meta);
            points.add(temp);
        }
        return points;
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
    public Location getToPoint() {
        return to;
    }
    public void setFromPoint(Location location) {
        from = location;
    }
    public void setToPoint(Location location) {
        to = location;
    }
    public ItemStack getItemStack() {
        return itemStack;
    }
}
