package cf.motastish.kaktusclicker.utils;

import cf.motastish.kaktusclicker.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class config {
    static Main plugin = Main.getInstance();

    public config(){
        loadconfig();
    }

    FileConfiguration plconfig = plugin.getConfig();
    File dir = plugin.getDataFolder();

    File file = new File(dir, "playerdata.yml");
    FileConfiguration dataconfig = YamlConfiguration.loadConfiguration(file);

    private void loadconfig(){
        plconfig.addDefault("messages.prefix", "§7[§f§lK§fC§7]");

        plconfig.addDefault("options.defaultCooldown", 100);
        plconfig.addDefault("options.disableDamage", true);
        plconfig.addDefault("options.disableDamage", true);
        plconfig.options().copyDefaults(true);
        try {
            if (!dir.exists()) dir.mkdir();
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        plugin.saveConfig();
    }

    public static void reloadconfig(){

    }




}
