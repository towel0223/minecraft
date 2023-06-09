package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Mafia extends Citizen {
    Location mafiaLocation;
    Mafia(MafiaPlugin plugin) {

        super(plugin);
        super.job = ChatColor.BLACK + "마피아";
    }
    //mafiaTime T:밤 F:낮
    @EventHandler
    public void mafiaTeleport(PlayerTeleportEvent e) {

        if (mafiaTime) {
            PotionEffect effect = new PotionEffect(PotionEffectType.INVISIBILITY, 12000, 1);
            player.addPotionEffect(effect);
        } else {
            player.removePotionEffect(PotionEffectType.INVISIBILITY); // INVISIBILITY 효과 제거
        }
    }
    @Override
    public void MafiaTeleport(Player p) {
        if (mafiaTime) {
            mafiaLocation = new Location(player.getWorld(), 115, 57, 384);
            player.teleport(mafiaLocation);
        }
    }

    @EventHandler
    public void MafiaMove(PlayerMoveEvent e){
        // 플레이어가 취소 상태인지 확인
        if (plugin.mafiaRun) {
           player.setWalkSpeed(0.0f);
           player.sendMessage(ChatColor.RED+"10초 동안 움직일 수 없습니다.");
        }
        else{
            player.setWalkSpeed(0.2f);
        }

    }


}