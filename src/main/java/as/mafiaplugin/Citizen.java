package as.mafiaplugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Citizen implements Listener, CommandExecutor {

    protected String job;
    Player player;
    List<Player> players = new ArrayList<Player>();

    boolean mafiaTime = true;

    boolean mafiaTime=true;
    MafiaPlugin plugin;


    Citizen(MafiaPlugin plugin) {
        job = ChatColor.YELLOW + "시민";
        this.plugin=plugin;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getJob() {
        return job;
    }

    public void setPlayerAdd(Player player) {
        players.add(player);
    }

    public Player getPlayer() {
        return player;
    }
    public Player getPlayer(String name)
    {
        for(Player all: players){
            if(all.getName().equals(name))
            {
                return all;
            }
        }
        return null;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }



    public class mafiaTime {
        boolean mafiaTime;

        public void setMafiaTimeMorning() {
            mafiaTime = false;
            

        }

        public void setMafiaTimeNight() {
            mafiaTime = true;


        }
    }


    private boolean isMafia(Player player) {
        // 플레이어의 직업을 가져온다.
        String job = getJobOfPlayer(player);

        // 직업이 마피아인지 확인한다.
        if (job.contains("마피아")&& job != null ) {
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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("start")) {
                if (plugin.getCount() >= 1) {
                    Collections.shuffle(plugin.People);
                    for (int i = 0; i < plugin.People.size(); i++) {
                        plugin.job[i].setPlayer(plugin.People.get(i));
                        plugin.job[0].setPlayerAdd(plugin.People.get(i));
                        plugin.People.get(i).sendMessage(ChatColor.WHITE + "당신의 직업은 " + plugin.job[i].getJob() + ChatColor.WHITE + " 입니다!");
                    }

                    for (Player all : plugin.People) { // 마피아게임 밤
                        all.sendTitle("마피아 게임", ChatColor.DARK_RED + "밤", 20, 40, 20);
                    }

                    // 마피아 플레이어들을 텔레포트
                    for (Player mafiaPlayer : plugin.getJobOfPlayer("마피아")) {
                        // 마피아 플레이어의 원하는 좌표 설정
                        Location mafiaLocation = new Location(player.getWorld(), 1, 2,3);
                        mafiaPlayer.teleport(mafiaLocation);
                    }

                    // 시민(시민과 같은 편) 플레이어들을 랜덤한 장소로 텔레포트
                    List<Location> citizenLocations = new ArrayList<>();
                    // 시민 플레이어를 텔레포트할 여러 장소의 좌표를 설정
                    citizenLocations.add(new Location(player.getWorld(), 1, 1, 1));
                    citizenLocations.add(new Location(player.getWorld(), 2, 2, 2));
                    citizenLocations.add(new Location(player.getWorld(), 3, 3, 3));
                    // ... 다른 장소들 추가

                    for (Player citizenPlayer : plugin.getPlayersByJob("시민")) {
                        // 랜덤한 장소 선택
                        Location randomLocation = citizenLocations.get(new Random().nextInt(citizenLocations.size()));
                        citizenPlayer.teleport(randomLocation);
                    }

                    for (Player all : plugin.People) { // 마피아게임 낮
                        all.sendTitle("마피아 게임", ChatColor.YELLOW + "낮", 20, 40, 20);
                    }


                    // 직업에 따라 추가 동작 수행
                    String playerJob = getJobOfPlayer(player);
                    if (playerJob != null) {
                        if (playerJob.equalsIgnoreCase("마피아")) {
                            // 마피아인 경우의 추가 동작 수행

                        } else if (playerJob.equalsIgnoreCase("시민")) {
                            // 시민인 경우의 추가 동작 수행

                        }
                    }

                     //코드 +
                } else {
                    player.sendMessage(ChatColor.RED + "플레이어수가 부족해 게임을 시작할 수 없습니다.");
                }
                return true;
            }
        }
        return false;
    }



    @EventHandler
    public void Death(PlayerDeathEvent event)
    {
        if(mafiaTime)
        {
          mafiaTime=false;
        }
        else
        {
          mafiaTime=true;
        }
    }

    public void MafiaTeleport(Player player) {
    }
}

