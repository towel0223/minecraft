package as.mafiaplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.World;
import org.bukkit.Location;

public final class MafiaPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);

        // Plugin startup logic

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void onPlayerInteract(PlayerToggleSprintEvent event) {
        Player player = event.getPlayer();
        Location eyeLocation = player.getEyeLocation();  // 플레이어의 눈의 위치 가져오기
        World world = player.getWorld();
        if(event.isSprinting()) {
            world.strikeLightning(eyeLocation);  // 번개 생성
            world.createExplosion(eyeLocation, 5, true);  // 폭발 생성
        }
    }



}
