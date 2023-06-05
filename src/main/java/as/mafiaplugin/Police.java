package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Police extends Citizen {
    private Park park;
    private Citizen playerJob;

    public Police(MafiaPlugin plugin) {
        super(plugin);
        super.job = ChatColor.BLUE + "경찰";
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public void setPlayerJob(Player player, Citizen job) {
        playerJob = job;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("search")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "플레이어만 이 명령어를 사용할 수 있습니다.");
                return true;
            }
            if (!(playerJob instanceof Police)) {
                sender.sendMessage(ChatColor.RED + "경찰만 이 명령어를 사용할 수 있습니다.");
                return true;
            }

            if (park != null) {
                List<Player> players = park.getPlayers(); // Park 클래스의 플레이어 목록을 가져옴
                for (Player target : players) {
                    if (target.getName().equals(args[0])) {
                        if (park.isMafia(target)) {
                            player.sendMessage(ChatColor.YELLOW + "조사한 플레이어 " + target.getName() + "은(는) 마피아입니다.");
                        } else {
                            player.sendMessage(ChatColor.GREEN + "조사한 플레이어 " + target.getName() + "은(는) 마피아가 아닙니다.");
                        }
                        return true;
                    }
                }
                player.sendMessage(ChatColor.RED + "해당 플레이어를 찾을 수 없습니다.");
            } else {
                player.sendMessage(ChatColor.RED + "경찰이 파크를 설정하지 않았습니다.");
            }
        }

        return true;
    }
}