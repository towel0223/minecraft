package as.mafiaplugin;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MafiaPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        getCommand("ready").setExecutor(new Song());
        // Plugin startup logic

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




}
