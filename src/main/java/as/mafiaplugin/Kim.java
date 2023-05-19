package as.mafiaplugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Kim {

    public class GameStartListener {

        private JavaPlugin plugin;
        private int minPlayers = 4; // 최소 플레이어 수
        private boolean gameStarted = false; // 게임 시작 여부

        public GameStartListener(JavaPlugin plugin) {
            this.plugin = plugin;
        }

        public void startGame() {
            // 게임 시작 시 실행될 내용
            gameStarted = true;
            Location destination = new Location(Bukkit.getWorld("world"), X, Y, Z); // A 장소의 좌표(X, Y, Z)로 설정

            // 5초 후에 A 장소로 이동
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.teleport(destination);
                    }
                }
            }.runTaskLater(plugin, 100L); // 20 틱 = 1초, 5초 = 100틱 (20 * 5)
        }

        public boolean canStartGame() {
            int onlinePlayers = Bukkit.getOnlinePlayers().size();
            return !gameStarted && onlinePlayers >= minPlayers;
        }

        // 플레이어 입장 시 실행될 메소드 (이벤트 리스너 등록 필요)
        public void onPlayerJoin(Player player) {
            if (canStartGame()) {
                Bukkit.broadcastMessage("5초 뒤에 시작");
                startGame();
            }
        }
    }

}
