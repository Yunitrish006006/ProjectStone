package project_stone.project_stone.API;

import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class systemAPI {
    public static boolean IDMatch(ItemStack i1,ItemStack i2) {
        return Objects.requireNonNull(i1.getItemMeta()).getLocalizedName().split(",")[1].equalsIgnoreCase(Objects.requireNonNull(i2.getItemMeta()).getLocalizedName().split(",")[1]);
    }
}
