package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Citizen implements Listener {

    protected String job;
    Player player;

   Citizen(){
        job=ChatColor.YELLOW+"시민";
    }
    public void setPlayer(Player player){
        this.player=player;
   }
    public String getJob()
    {
        return job;
    }
    @EventHandler
    public void ducking(PlayerJoinEvent e) {
        Player player = e.getPlayer();

    }

}
