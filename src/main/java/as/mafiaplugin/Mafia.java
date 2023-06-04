package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Mafia extends Citizen {
    Mafia() {
        super.job = ChatColor.BLACK + "마피아";
    }
    //mafiaTime T:밤 F:낮

    @EventHandler
    public void mafiaTeleport(PlayerTeleportEvent e){
        Player player = e.getPlayer();
    if(mafiaTime){
        PotionEffect effect = new PotionEffect(PotionEffectType.INVISIBILITY,999 , 0);
        player.addPotionEffect(effect);
    }
    }
}
