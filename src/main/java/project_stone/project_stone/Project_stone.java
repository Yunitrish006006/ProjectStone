package project_stone.project_stone;
import org.bukkit.plugin.java.JavaPlugin;
import project_stone.project_stone.VoidSystem.AnchorFinder;
import project_stone.project_stone.VoidSystem.void_anchor;
import project_stone.project_stone.events.OnPlayerDeath;
import project_stone.project_stone.events.OnPlayerFished;

import java.util.Objects;

public final class Project_stone extends JavaPlugin {

    private static Project_stone plugin;
    public static Project_stone getPlugin() {
        return plugin;
    }
    public static String getRootFolder() {
        return plugin.getDataFolder().getPath();
    }
    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        Objects.requireNonNull(getCommand("void_anchor")).setExecutor(new void_anchor());
        Objects.requireNonNull(getCommand("void_anchor")).setTabCompleter(new AnchorFinder());
        void_anchor.initialize();
        getServer().getPluginManager().registerEvents(new OnPlayerFished(),this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(),this);
        }

    @Override
    public void onDisable() {
    }

}
