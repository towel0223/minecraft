package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Doctor extends Citizen {
    Doctor() {
        super.job = ChatColor.GRAY + "의사";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (mafiaTime) {
            if (command.getName().equalsIgnoreCase("protect")) {
                if (strings.length == 1) {
                    String playerName = strings[0];

                    // 플레이어 객체 가져오기
                    Player player = plugin.getPlayer(playerName);

                    // 플레이어가 존재하고 온라인인 경우
                    if (player != null && player.isOnline()) {
                        // 대상 플레이어가 "마피아"인지 확인
                        MafiaPlugin mafiaPlugin = Plugin(MafiaPlugin.class);
                        boolean isMafia = mafiaPlugin.isMafia(player);

                        // 플레이어에게 데미지 면역 효과 부여
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 5, false, false));

                        commandSender.sendMessage(ChatColor.GREEN + player.getName() + "님을 치료합니다.");

                        // 대상 플레이어가 "마피아"인 경우
                        if (isMafia) {
                            mafiaPlugin.MafiaTime(false);
                            player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                        }

                        return true;
                    } else {
                        commandSender.sendMessage(ChatColor.RED + "잘못입력하셨습니다.");
                        return true;
                    }
                } else {
                    commandSender.sendMessage(ChatColor.RED + "사용법 : /protect <플레이어이름>");
                    return true;
                }
            }
        }

        return false;
    }
}