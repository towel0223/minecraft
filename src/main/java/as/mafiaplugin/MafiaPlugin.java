package as.mafiaplugin;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MafiaPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
<<<<<<< HEAD
        getCommand("ready").setExecutor(new Song());
=======

 System.out.println("My first plugin has started!! hello!!");
 getCommand("blockbreak").setExecutor(new Han());
    }        // Plugin startup logic


>>>>>>> aab613f8e1c52bd1dd6ddf41f76902be83dc05ab
        // Plugin startup logic


}
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




}
