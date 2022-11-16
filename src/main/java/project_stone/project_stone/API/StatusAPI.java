package project_stone.project_stone.API;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import project_stone.project_stone.Project_stone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatusAPI {
    public static void setPlayerNBT(Player player,String tag,String value) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        if(value.equalsIgnoreCase("null")) {
            data.remove(Objects.requireNonNull(NamespacedKey.fromString(tag, Project_stone.getPlugin())));
            data.remove(Objects.requireNonNull(NamespacedKey.fromString(tag)));
        }
        data.set(Objects.requireNonNull(NamespacedKey.fromString(tag, Project_stone.getPlugin())), PersistentDataType.STRING,value);
    }
    public static List<String> getPlayerNBT(Player player) {
        PersistentDataContainer mc = player.getPersistentDataContainer();
        List<NamespacedKey> keys = new ArrayList<>(mc.getKeys());
        List<String> result = new ArrayList<>();
        for (NamespacedKey key : keys) {
            result.add(key.toString().replaceFirst("minecraft:","") + " : " + mc.get(key, PersistentDataType.STRING));
        }
        return result;
    }
}
