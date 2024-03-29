package project_stone.project_stone.VoidTech.tools.voidBook;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import project_stone.project_stone.VoidTech.Anchor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static project_stone.project_stone.API.TextAPI.keyAndValueText;

public class VoidBook {
    private Location from;
    private Location to;
    private static ItemStack itemStack;
    public VoidBook() {
        itemStack = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("Void Book");
        itemMeta.setCustomModelData(1);
        List<String> lore = new ArrayList<>();
        lore.add("a book that can bring u anywhere");
        itemMeta.setLore(lore);
        itemMeta.setLocalizedName("ProjectStone:anchor,void_book");
        itemStack.setItemMeta(itemMeta);
    }
    public Inventory GUI_hub(Player player) {
        Inventory main = Bukkit.createInventory(null,36,"Teleport Hub");
        main.setContents(points(player).toArray(new ItemStack[0]));
        ItemStack add = new ItemStack(Material.NAME_TAG);
        ItemMeta add_meta = add.getItemMeta();
        assert add_meta != null;
        add_meta.setDisplayName("add unit");
        add_meta.setLocalizedName("ProjectStone:anchor,add_unit");
        add.setItemMeta(add_meta);
        main.addItem(add);
        return main;
    }
    public Inventory GUI_set(Anchor anchor) {
        Inventory main = Bukkit.createInventory(null,9,anchor.getAnchor_name()+" Setting");

        ItemStack name = new ItemStack(Material.NAME_TAG);
        ItemMeta name_meta = name.getItemMeta();
        assert name_meta != null;
        name_meta.setDisplayName("SetName");
        name_meta.setLocalizedName("ProjectStone:anchor,set_name,"+anchor.getAnchor_name());
        name.setItemMeta(name_meta);
        main.setItem(1,name);

        ItemStack delete = new ItemStack(Material.FLINT_AND_STEEL);
        ItemMeta delete_meta = delete.getItemMeta();
        assert delete_meta != null;
        delete_meta.setDisplayName("Delete");
        delete_meta.setLocalizedName("ProjectStone:anchor,delete,"+anchor.getAnchor_name());
        delete.setItemMeta(delete_meta);
        main.setItem(2,delete);
        return main;
    }
    public Inventory GUI_set_name(Anchor anchor) {
        Inventory window = Bukkit.createInventory(null,InventoryType.ANVIL, "Enter Name");

        ItemStack temp = new ItemStack(Objects.requireNonNull(Material.BOOK));
        ItemMeta meta = temp.getItemMeta();
        assert meta != null;
        meta.setLocalizedName("ProjectStone:anchor,modify_unit,"+anchor.getAnchor_name());
        meta.setDisplayName(ChatColor.RESET+anchor.getAnchor_name());
        temp.setItemMeta(meta);
        window.setItem(0,temp);
        return window;
    }
    public List<ItemStack> points(Player player) {
        List<ItemStack> points = new ArrayList<>();
        for(String a_name : Anchor.getOwnedAnchorNameList(player)) {
            Anchor anchor = new Anchor().get(a_name);
            ItemStack temp = new ItemStack(Objects.requireNonNull(Material.getMaterial(anchor.getIcon().toUpperCase())));
            ItemMeta meta = temp.getItemMeta();
            List<String> lores = new ArrayList<>();
            lores.add(keyAndValueText("purview",anchor.getPurview()));
            lores.add(keyAndValueText("gravity",String.valueOf(anchor.isGravity())));
            lores.add(keyAndValueText("wait time",String.valueOf(anchor.getWait_time())));
            lores.add(keyAndValueText("position",anchor.getPureLocation()));
            assert meta != null;
            meta.setLore(lores);
            meta.setLocalizedName("ProjectStone:anchor,unit,"+anchor.getAnchor_name());
            meta.setDisplayName(ChatColor.RESET+anchor.getAnchor_name());
            temp.setItemMeta(meta);
            points.add(temp);
        }
        return points;
    }
    public ItemMeta getItemMeta() {
        return itemStack.getItemMeta();
    }
    public List<String> getLore() {
        return Objects.requireNonNull(itemStack.getItemMeta()).getLore();
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
