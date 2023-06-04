package as.mafiaplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;


public final class MafiaPlugin extends JavaPlugin {

    private Park ParkExecutor;

    private int count=0;
    List<Player> People=new ArrayList<Player>();
    Citizen[] job;



    boolean mafiaRun=true;


    @Override
    public void onEnable() {
        job=new Citizen[4];
        job[0]=new Mafia(this);
        job[1]=new Police(this);
        job[2]=new Doctor(this);
        job[3]=new Citizen(this);
        getServer().getPluginManager().registerEvents(job[0],this);
        getServer().getPluginManager().registerEvents(job[1],this);
        getServer().getPluginManager().registerEvents(job[2],this);
        getServer().getPluginManager().registerEvents(job[3],this);
        ParkExecutor=new Park(this);
        getCommand("ready").setExecutor(ParkExecutor);
        getCommand("start").setExecutor(ParkExecutor);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public int getCount(){
        return count;
    }
    public void setCount()
    {
        this.count++;
    }


    public List<String> getPlayerName(){
        List<String> name=new ArrayList<String>();
            for(int i=0; i<People.size(); i++) {

                name.add(People.get(i).getName());
        }
        return name;
    }
    public Player getPlayer(String playerName)
    {
        Player targetplayer;
        for(Player all: People)
        {
            if(all.getName().equals(playerName))
                return all;
        }


        return null;

    }


}