package project_stone.project_stone;
import org.bukkit.plugin.java.JavaPlugin;
import project_stone.project_stone.API.DiscordWebhook;
import project_stone.project_stone.DTech.onShoot;
import project_stone.project_stone.System.SystemTabCompleter;
import project_stone.project_stone.System.WorldManager;
import project_stone.project_stone.VoidTech.Anchor;
import project_stone.project_stone.VoidTech.AnchorFinder;
import project_stone.project_stone.VoidTech.tools.voidBook.OnUsingBook;
import project_stone.project_stone.VoidTech.tools.void_wand.OnUsingVoidWand;
import project_stone.project_stone.VoidTech.void_anchor;
import project_stone.project_stone.System.System;
import project_stone.project_stone.commands.fly;
import project_stone.project_stone.commands.hat;
import project_stone.project_stone.commands.light;
import project_stone.project_stone.events.*;

import java.util.Objects;

public final class Project_stone extends JavaPlugin {

    private String webhook_url = "https://discord.com/api/webhooks/1068513559446835301/rk4Tpe_Wy0JTbbiAzWVXqZsDRH3QpoMWBqx67fw28V7ZmhGtpVbGW7m6pqOQxEuAt70Z";

    private static Project_stone plugin;
    public static Project_stone getPlugin() {
        return plugin;
    }
    public static String getRootFolder() {
        return plugin.getDataFolder().getPath();
    }
    public static String version = "beta 1.0";
    @Override
    public void onEnable() {
        plugin = this;
        DiscordWebhook webhook = new DiscordWebhook(webhook_url);
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription("server is starting"));
        try {
            webhook.execute();
        }
        catch (java.io.IOException e){
            getLogger().severe(e.getStackTrace().toString());
        }
        this.saveDefaultConfig();
        Anchor.initialize();
        WorldManager.initial();
        Objects.requireNonNull(getCommand("void_anchor")).setExecutor(new void_anchor());
        Objects.requireNonNull(getCommand("void_anchor")).setTabCompleter(new AnchorFinder());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new fly());
        Objects.requireNonNull(getCommand("hat")).setExecutor(new hat());
        Objects.requireNonNull(getCommand("light")).setExecutor(new light());
        Objects.requireNonNull(getCommand("system")).setExecutor(new System());
        Objects.requireNonNull(getCommand("system")).setTabCompleter(new SystemTabCompleter());
        getServer().getPluginManager().registerEvents(new OnUsingBook(),this);
        getServer().getPluginManager().registerEvents(new OnPlayerFished(),this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(),this);
        getServer().getPluginManager().registerEvents(new onShoot(),this);
        getServer().getPluginManager().registerEvents(new OnCreeperExplode(),this);
        getServer().getPluginManager().registerEvents(new OnUsingVoidWand(),this);
        getServer().getPluginManager().registerEvents(new OnPlayerPortal(),this);
        getServer().getPluginManager().registerEvents(new OnPlayerChat(),this);
    }

    @Override
    public void onDisable() {
        DiscordWebhook webhook = new DiscordWebhook(webhook_url);
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription("server shutting down"));
        try {
            webhook.execute();
        }
        catch (java.io.IOException e){
            getLogger().severe(e.getStackTrace().toString());
        }
    }
}
