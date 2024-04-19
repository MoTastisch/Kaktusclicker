package cf.motastish.kaktusclicker.listener;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class cancelevent implements Listener {
    static FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("Kaktusclicker").getConfig();

    @EventHandler
    public void kaktusevent(EntityDamageEvent e) {
        if(e.getCause()==EntityDamageEvent.DamageCause.CONTACT){
            if(config.getBoolean("options.disableDamage")) e.setCancelled(true);
        }
    }
    @EventHandler
    public void kaktuskaputt(BlockBreakEvent e) {
        if(e.getBlock().getType()!= Material.CACTUS) return;
        if(!e.getPlayer().isSneaking()) e.setCancelled(true);
    }
    @EventHandler
    public void kaktuswachs(BlockGrowEvent e) {
        if(e.getBlock().getType()== Material.CACTUS) e.setCancelled(true);
    }


}
