package cf.motastish.kaktusclicker.commands;

import cf.motastish.kaktusclicker.utils.ymlmanager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class kaktusclicker implements CommandExecutor {

    File dir = Bukkit.getServer().getPluginManager().getPlugin("Kaktusclicker").getDataFolder();

    File file = new File(dir, "playerdata.yml");

    FileConfiguration dataconfig = YamlConfiguration.loadConfiguration(file);
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("Kaktusclicker").getConfig();
    String prefix = config.getString("messages.prefix");


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID id = null;
            Player p = null;


            if(args.length == 0){
                player.sendMessage(prefix+" §cUsage: §e/kaktusclicker [<set, get>]");
                return false;
            }
            p = Bukkit.getPlayer(args[1]);
            if (p != null) id = p.getUniqueId();

            if(args.length == 1) {
                if (args[0].equalsIgnoreCase("set")) {
                    player.sendMessage(prefix+" §cUsage: §e[set] [<player>] [<amount>]");
                } else if (args[0].equalsIgnoreCase("get")){
                    player.sendMessage(prefix+" §cUsage: §e[get] [<player>]");
                } else player.sendMessage(prefix+" §cUsage: §e/kaktusclicker [<set, get>]");

            } else if(args.length == 2) {
                if (args[0].equalsIgnoreCase("set")) {
                    player.sendMessage(prefix+" §cUsage: §e[set] [<player>] [<amount>]");
                } else if (args[0].equalsIgnoreCase("get")) {
                    player.sendMessage(prefix + " §7Der Spieler §e" + p.getDisplayName() + " §7hat §e" + ymlmanager.playerdataGetint(id + ".kaktuscount") + "§7 Kakteen");
                } else player.sendMessage(prefix+" §cUsage: §e/kaktusclicker [<set, get>]");

            } else if(args.length == 3){
                if (args[0].equalsIgnoreCase("set")) {
                    player.sendMessage(prefix+" §7Der Wert §e"+args[2]+" §7wurde für §e"+args[1]+" §7erfolgreich gesetzt!");
                    ymlmanager.playerdataSetInt(id+".kaktuscount", Integer.parseInt(args[2]));
                    try {
                        dataconfig.save(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else player.sendMessage(prefix+" §cUsage: §e/kaktusclicker [<set, get>]");
            }


        }

        return true;
        }


}
