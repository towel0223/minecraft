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


    @EventHandler
    public void ducking(PlayerDeathEvent e) {
        List<String> name=new ArrayList<String>();

        for (Player qwer : players) {
            name.add(qwer.getName());
        }
        for(Player asdf: players){
            asdf.sendMessage(name.toString());
        }

    }
}
