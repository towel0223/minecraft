package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Police extends Citizen {
    private final MafiaPlugin plugin; // MafiaPlugin 객체를 참조하기 위한 변수

    public Police(MafiaPlugin plugin) {
        this.plugin = plugin;
        super.job = ChatColor.BLUE + "경찰";
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("search")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "플레이어만 이 명령어를 사용할 수 있습니다.");
                return true;
            }

            Player player = (Player) sender;

            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "플레이어 이름을 입력해주세요.");
                return true;
            }

            String targetPlayerName = args[0];
            Player targetPlayer = player.getServer().getPlayer(targetPlayerName);

            if (targetPlayer == null) {
                player.sendMessage(ChatColor.RED + "해당 플레이어를 찾을 수 없습니다.");
                return true;
            }

            // 해당 플레이어의 역할 확인 및 알려주기
            if (isMafia(targetPlayer)) {
                player.sendMessage(ChatColor.WHITE + targetPlayer.getName() + "님은 마피아입니다.");
            } else {
                player.sendMessage(ChatColor.WHITE + targetPlayer.getName() + "님은 마피아가 아닙니다.");
            }

            return true;
        }

        return false;
    }

    private boolean isMafia(Player player) {
        // 플레이어의 직업을 가져온다.
        String job = getJobOfPlayer(player);

        // 직업이 마피아인지 확인한다.
        if (job.equalsIgnoreCase(ChatColor.RED + "마피아")) {
            return true; // 마피아인 경우 true 반환
        } else {
            return false; // 마피아가 아닌 경우 false 반환
        }
    }

    private String getJobOfPlayer(Player player) {
        // 플레이어의 직업을 확인하여 반환하는 로직을 구현합니다.

        for (int i = 0; i < plugin.job.length; i++) {
            if (plugin.job[i].getPlayer() == player) {
                return plugin.job[i].getJob();
            }
        }
        return null; // 직업을 찾지 못한 경우 null을 반환합니다.
    }
}
