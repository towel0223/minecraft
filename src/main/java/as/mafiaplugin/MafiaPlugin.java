package as.mafiaplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class MafiaPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

 System.out.println("My first plugin has started!! hello!!");
    }        // Plugin startup logic


    @Override
    public void onDisable() {
        System.out.println("My first plugin has stopped!! bye!!");

        // Plugin shutdown logic
    }
}
