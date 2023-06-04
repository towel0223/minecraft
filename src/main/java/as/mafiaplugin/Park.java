package as.mafiaplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.BarColor;

import java.util.Collections;
import java.util.Random;

public class Park implements CommandExecutor {
    BossBar bossBar=Bukkit.createBossBar("남은 시간",BarColor.BLUE, BarStyle.SOLID); //남은시간
    final MafiaPlugin plugin;
    private final Police police;
    private Park park;

    public Park(MafiaPlugin plugin) {
        this.plugin = plugin;
        this.police = new Police(plugin); // 수정
        this.police.setPark(this); //추가함
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("ready")) { //게임준비

                if (plugin.getPlayerName().contains(player.getName())) {
                    player.sendMessage(ChatColor.RED + "이미 게임에 참여하셨습니다.");
                    return true;
                }



                if (plugin.getCount() < 12) {
                    plugin.setCount();

                    if(!plugin.getPlayerName().contains(player.getName()))
                        player.sendMessage(ChatColor.AQUA + "게임 준비를 완료했습니다.");
                    plugin.People.add(player);
                    for(Player player2 : plugin.People) {
                        player2.sendMessage(ChatColor.AQUA + "현재 게임 참가한 인원: " + plugin.getCount());
                    }
                    return true;

                } else {
                    player.sendMessage(ChatColor.RED + "플레이어가 꽉차 게임을 할 수 없습니다!");
                    return true;
                }}
            if(command.getName().equalsIgnoreCase("start")){  //게임시작
                if(plugin.getCount()>=1) {
                    Collections.shuffle(plugin.People);
                    for (int i = 0; i < plugin.People.size(); i++) {
                        plugin.job[i].setPlayer(plugin.People.get(i));
                        plugin.People.get(i).sendMessage(ChatColor.WHITE + "당신의 직업은 " + plugin.job[i].getJob() + ChatColor.WHITE + " 입니다!");
                    }


                    police.setPark(this);  // Police 클래스에 현재 Park 인스턴스를 전달[수정한 부분]
                    plugin.getCommand("search").setExecutor(new Police(plugin));
                    plugin.getCommand("protect").setExecutor(new Doctor(plugin));
                    plugin.job[0].MafiaTeleport(plugin.job[0].getPlayer());
                    for (Player all : plugin.People) { //마피아게임 밤
                        all.sendTitle("마피아 게임", ChatColor.DARK_PURPLE + "밤", 20, 40, 20);
                    }
                    police.setPark(this); //추가함

                    for (Player all : plugin.People) { //마피아게임 낮
                        all.sendTitle("마피아 게임", ChatColor.YELLOW + "낮", 20, 40, 20);
                    }
                    for (Player player3 : plugin.People) {
                        bossBar.addPlayer(player3); //각자 플레이어에게 보스바 부여
                    }
                    Bukkit.getScheduler().runTaskTimer(plugin, () -> { //1초(20)마다 반복 0초후에 시작
                        double progress = bossBar.getProgress(); //시간 가져오기
                        bossBar.setProgress(progress - 0.01f);  //남은시간(-1초씩 빼기 총 100초)
                        if (Math.abs(bossBar.getProgress()) < 0.01f) { //0초되면 보스바가 사라짐
                            bossBar.removeAll();
                        }
                    }, 0, 20);
                }

                return true;
            }
            else{
                player.sendMessage(ChatColor.RED+"플레이어수가 부족해 게임을 시작할 수 없습니다.");
                return true;
            }
        }

        return false;
    }



}