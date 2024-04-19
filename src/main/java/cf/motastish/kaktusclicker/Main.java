package cf.motastish.kaktusclicker;

import cf.motastish.kaktusclicker.commands.kaktusclicker;
import cf.motastish.kaktusclicker.listener.cancelevent;
import cf.motastish.kaktusclicker.listener.click;
import cf.motastish.kaktusclicker.utils.config;
import cf.motastish.kaktusclicker.utils.ymlmanager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;



public final class Main extends JavaPlugin {

    public static Main instance;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        Bukkit.getConsoleSender().sendMessage("§2Kaktusclicker wurde geladen §7- by MoTastish ;)");

        getCommand("kaktusclicker").setExecutor(new kaktusclicker());

        getServer().getPluginManager().registerEvents(new click(), this);
        getServer().getPluginManager().registerEvents(new cancelevent(), this);

        new config();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ymlmanager.playerdatasave();

    }

    public static Main getInstance() {
        return instance;
    }

}
