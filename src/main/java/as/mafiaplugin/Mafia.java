package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Mafia extends Citizen {
    Mafia(){
        super.job=ChatColor.BLACK+"마피아";
    }



}

