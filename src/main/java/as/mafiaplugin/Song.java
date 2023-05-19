package as.mafiaplugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public class Song {


    public class ReadyComplete implements CommandExecutor { //준비완료 기능(지정된 장소 이동,준비 확인 변수 ready

        private boolean ready = false;

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
                return true;
            }

            Player player = (Player) sender;

            if (args.length == 1 && args[0].equalsIgnoreCase("준비완료")) {
                ready = true;
                player.sendMessage("준비 완료!");
                Location targetLocation = new Location(player.getWorld(), 207.917, 60.5, 292.932);
                player.teleport(targetLocation);
                return true;
            }

            return false;
        }

        public boolean isReady() {
            return ready;
        }
    }
}