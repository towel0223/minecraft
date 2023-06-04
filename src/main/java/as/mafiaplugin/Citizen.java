package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class Citizen implements Listener {

    protected String job;
    Player player;
    List<Player> players = new ArrayList<Player>();


    Citizen() {
        job = ChatColor.YELLOW + "시민";
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getJob() {
        return job;
    }

    public void setPlayerAdd(Player player) {
        players.add(player);
    }


    public class mafiaTime {
        boolean mafiaTime;
        public void setMafiaTimeMorning() {
            mafiaTime = false;


        }

        public void setMafiaTimeNight() {
            mafiaTime = true;


        }
    }
}
