package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Doctor extends Citizen {
    Doctor(MafiaPlugin plugin){
        super(plugin);
        super.job=ChatColor.GRAY+"의사";
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("protect")) {
            if (strings.length == 1) {
                String playerName = strings[0];

                // 플레이어 객체 가져오기
                Player player = plugin.getPlayer(playerName);

                // 플레이어가 존재하고 온라인인 경우
                if (player != null && player.isOnline()) {
                    // 플레이어에게 데미지 면역 효과 부여
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 5, false, false));

                    commandSender.sendMessage(ChatColor.GREEN + playerName + "님을 지목했습니다.");
                    return true;
                } else {
                    commandSender.sendMessage(ChatColor.RED + "유효한 플레이어 이름을 입력해주세요.");
                    return true;
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "사용법: /protect <플레이어이름>");
                return true;
            }
        }
        return false;
    }







}
