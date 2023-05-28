package as.mafiaplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;


public final class MafiaPlugin extends JavaPlugin {

    private Park ParkExecutor;
    private boolean Participation=false;
    private int count=0;
    List<Player> People=new ArrayList<Player>();
    Citizen[] job;






    @Override
    public void onEnable() {
        job=new Citizen[4];
        job[0]=new Citizen();
        job[1]=new Police();
        job[2]=new Mafia();
        job[3]=new Doctor();
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
    public boolean getParticipation(){
        return Participation;
    }
    public void setParticipation(boolean Participation)
    {
        this.Participation=Participation;
    }
    public int getCount(){
        return count;
    }
    public void setCount()
    {
        this.count++;
    }

}
