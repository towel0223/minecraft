package as.mafiaplugin;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MafiaPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);

        getCommand("ready").setExecutor(new Song());

 System.out.println("My first plugin has started!! hello!!");
 getCommand("blockbreak").setExecutor(new Han());




}
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




}
