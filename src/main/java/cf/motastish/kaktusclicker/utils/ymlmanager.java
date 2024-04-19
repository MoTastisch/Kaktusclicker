package cf.motastish.kaktusclicker.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ymlmanager {

    static File dir = Bukkit.getServer().getPluginManager().getPlugin("Kaktusclicker").getDataFolder();


    //Player Data Config

    static File playerdatafile = new File(dir, "playerdata.yml");
    static FileConfiguration playerdata = YamlConfiguration.loadConfiguration(playerdatafile);

    public static void playerdatasave(){
        try {
            playerdata.save(playerdatafile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Bukkit.getConsoleSender().sendMessage("§2Kaktusclicker §7hat die Config §egespeichert!");
    }

    public static Integer playerdataGetint(String path){
        return playerdata.getInt(path);
    }
    public static void playerdataSetInt(String path, Integer wert) {
        playerdata.set(path,wert);
    }

}
