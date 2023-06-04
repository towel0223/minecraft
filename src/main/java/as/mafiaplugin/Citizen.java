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
import java.util.Collections;
import java.util.List;

public class Citizen implements Listener, CommandExecutor {
    MafiaPlugin plugin;
    Citizen(MafiaPlugin plugin)
    {
        this.plugin=plugin;
        job=ChatColor.WHITE+"시민";
    }


    protected String job;
    Player player;
    boolean mafiaTime=true;




    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getJob() {
        return job;
    }


    public Player getPlayer() {
        return player;
    }



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @EventHandler
    public void Death(PlayerDeathEvent event)
    {
        if(mafiaTime)
        {
            mafiaTime=false;
        }
        else
        {
            mafiaTime=true;
        }
    }

    public void MafiaTeleport(Player player) {
    }
}
