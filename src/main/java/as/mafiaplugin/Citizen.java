package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class Citizen implements Listener, CommandExecutor {

    protected String job;
    Player player;
    List<Player> players = new ArrayList<Player>();
    boolean mafiaTime = true;

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

    public Player getPlayer() {
        return player;
    }
    public Player getPlayer(String name)
    {
        for(Player all: players){
            if(all.getName().equalsIgnoreCase(name))
            {
                return all;
            }
        }
        return null;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
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