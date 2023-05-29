package as.mafiaplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.BarColor;
import java.util.Random;

public class Park implements CommandExecutor {
    Random rd = new Random();
    int a;
    BossBar bossBar=Bukkit.createBossBar("남은 시간",BarColor.BLUE, BarStyle.SOLID);
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
                    for(Player player2 : plugin.People) {
                        player2.sendMessage(ChatColor.AQUA + "현재 게임 참가한 인원: " + plugin.getCount());
                    }
                    return true;

                } else {
                    player.sendMessage(ChatColor.RED + "플레이어가 꽉차 게임을 할 수 없습니다!");
                    return true;
                }

            }
            if(command.getName().equalsIgnoreCase("start")){
                if(plugin.getCount()>=1) {
                    a = rd.nextInt(4);
                    plugin.job[a].setPlayer(plugin.People.get(0));
                    player.sendMessage(ChatColor.WHITE + "당신의 직업은 " + plugin.job[a].getJob() + ChatColor.WHITE + " 입니다!");
                        for (Player all : plugin.People) {
                            all.sendTitle("마피아 게임", ChatColor.DARK_PURPLE + "밤", 20, 40, 20);
                        }

                        for (Player all : plugin.People) {
                            all.sendTitle("마피아 게임", ChatColor.YELLOW + "낮", 20, 40, 20);
                        }
                    for (Player player3 : plugin.People) {
                        bossBar.addPlayer(player3);
                    }
                    Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                        double progress = bossBar.getProgress();
                        bossBar.setProgress(progress - 0.01f);
                        if(Math.abs(bossBar.getProgress())<0.01f){

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
