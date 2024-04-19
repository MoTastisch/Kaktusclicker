package cf.motastish.kaktusclicker.listener;

import cf.motastish.kaktusclicker.utils.ymlmanager;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class click implements Listener {

    //PluginConfig
    static FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("Kaktusclicker").getConfig();

    private Map<UUID, Long> lastExecutions = new HashMap<>();
    private static long INTERVAL = config.getInt("options.defaultCooldown");


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        UUID id = p.getUniqueId();
        int kaktuscounter = ymlmanager.playerdataGetint(id+".kaktuscount");

        if(INTERVAL<10) INTERVAL=10;


        if(event.getClickedBlock() == null) return;
        if(p.isSneaking()) return;

        if (event.getClickedBlock().getType() == Material.CACTUS) {
            event.setCancelled(true);

            boolean shouldExecute = false;
            if (lastExecutions.containsKey(id)) {
                if (lastExecutions.get(id) + INTERVAL <= System.currentTimeMillis()) {
                    shouldExecute = true;
                    lastExecutions.remove(id);
                }
            } else {
                shouldExecute = true;
            }

            if (shouldExecute) {
                lastExecutions.put(id, System.currentTimeMillis());

                p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 11);
                p.sendActionBar("§eCactus Count §2" + (kaktuscounter+1));
                ymlmanager.playerdataSetInt(id+".kaktuscount", (kaktuscounter+1));
            } else{
                p.sendActionBar("§cDu clickst zu §f§lschnell");
            }

        }
    }
}

