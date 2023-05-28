package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class Park implements CommandExecutor {
    Random rd = new Random();
    int a;
    private final MafiaPlugin plugin;

    public Park(MafiaPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("ready")) {

                if (plugin.getParticipation() == true) {
                    player.sendMessage(ChatColor.RED + "이미 게임에 참여하셨습니다.");
                    return true;
                }


                if (plugin.getCount() < 12) {
                    plugin.setCount();
                    plugin.setParticipation(true);
                    plugin.People.add(player);
                    player.sendMessage(ChatColor.AQUA + "게임 준비를 완료했습니다.");
                    player.sendMessage(ChatColor.AQUA + "현재 게임 참가한 인원: " + plugin.getCount());

                } else {
                    player.sendMessage(ChatColor.RED + "플레이어가 꽉차 게임을 할 수 없습니다!");
                }
                return true;
            }
            if(command.getName().equalsIgnoreCase("start")){
                if(plugin.getCount()>=1){
                    a=rd.nextInt(4);
                    plugin.job[a].setPlayer(plugin.People.get(0));
                    player.sendMessage(ChatColor.WHITE+"당신의 직업은 "+plugin.job[a].getJob()+ChatColor.WHITE+" 입니다!");
                }
                else{
                    player.sendMessage(ChatColor.RED+"플레이어수가 부족해 게임을 시작할 수 없습니다.");
                }
            }

        }

            return false;


    }
}