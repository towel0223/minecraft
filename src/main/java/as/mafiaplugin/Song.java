package as.mafiaplugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;


public class Song extends JavaPlugin implements CommandExecutor {
    private String[] preparedPlayers = new String[100]; // 최대 100명까지 저장 가능한 배열


    //준비완료 기능(지정된 장소 이동,준비 확인 변수 ready

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage("당신의 이름: " + player.getName());
        player.sendMessage("준비 여부: " + isPlayerReady(player));

        // 플레이어 이름과 준비 여부를 배열에 저장
        addPlayerToPreparedPlayers(player.getName(), isPlayerReady(player));

        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("이동합니다!");

                // 지정된 좌표로 이동
                Location targetLocation = new Location(player.getWorld(), 207.917, 60.5, 292.932);
                player.teleport(targetLocation);
            }
        }.runTaskLater(this, 5 * 20); // 5초 후에 실행되도록 지정합니다. 1초는 20틱입니다.

        return true;


        return false;
    }

    private boolean isPlayerReady(Player player) {
        // 플레이어의 준비 여부를 확인하는 로직을 구현합니다.
        // 예를 들어, 플레이어마다 별도의 준비 상태 변수를 가지고 있다면 그 값을 반환하거나,
        // 다른 플러그인이나 데이터베이스와 연동하여 준비 여부를 확인할 수도 있습니다.
        // 이 예시에서는 항상 준비 상태인 것으로 가정합니다.
        return true;
    }

    private void addPlayerToPreparedPlayers(String playerName, boolean isReady) {
        for (int i = 0; i < preparedPlayers.length; i++) {
            if (preparedPlayers[i] == null) {
                preparedPlayers[i] = playerName + ":" + isReady; // 이름과 준비 여부를 콜론(:)으로 구분하여 저장
                break;
            }
        }

    }
}