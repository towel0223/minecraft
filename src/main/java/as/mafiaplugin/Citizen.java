package as.mafiaplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class Citizen implements Listener, CommandExecutor {

    private boolean votingEnabled;
    private HashMap<Player, Integer> votes;
    protected String job;
    Player player;
    List<Player> players = new ArrayList<Player>();

    boolean mafiaTime = true;

    boolean mafiaTime = true;
    MafiaPlugin plugin;

    public void onEnable() {
        // 플러그인 활성화 시 실행되는 코드
        getServer().getPluginManager().registerEvents(this, this);
        votingEnabled = false;
        votes = new HashMap<>();
    }
    Citizen(MafiaPlugin plugin) {
        job = ChatColor.YELLOW + "시민";
        this.plugin = plugin;
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

    public Player getPlayer(String name) {
        for (Player all : players) {
            if (all.getName().equals(name)) {
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
        if (job.contains("마피아") && job != null) {
            return true;
        } else {
            return false;
        }
    }

    private String getJobOfPlayer(Player player) {


        for (int i = 0; i < plugin.job.length; i++) {
            if (plugin.job[i].getPlayer() == player) {
                return plugin.job[i].getJob();
            }
        }
        return null;
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
                        //밤 로직 추가
                    }


                    // 시민(시민과 같은 편) 플레이어들을 랜덤한 장소로 텔레포트
                    List<Location> citizenLocations = new ArrayList<>();
                    // 시민 플레이어를 텔레포트할 여러 장소의 좌표를 설정
                    citizenLocations.add(new Location(player.getWorld(), 1, 1, 1));
                    citizenLocations.add(new Location(player.getWorld(), 2, 2, 2));
                    citizenLocations.add(new Location(player.getWorld(), 3, 3, 3));
                    citizenLocations.add(new Location(player.getWorld(), 3, 3, 3));
                    citizenLocations.add(new Location(player.getWorld(), 3, 3, 3));
                    citizenLocations.add(new Location(player.getWorld(), 3, 3, 3));
                    citizenLocations.add(new Location(player.getWorld(), 3, 3, 3));
                    citizenLocations.add(new Location(player.getWorld(), 3, 3, 3));
                    citizenLocations.add(new Location(player.getWorld(), 3, 3, 3));
                    citizenLocations.add(new Location(player.getWorld(), 3, 3, 3));
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
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (votingEnabled && message.startsWith("/투표 ")) {
            if (message.length() > 7) {
                String targetPlayerName = message.substring(6);
                Player targetPlayer = Bukkit.getPlayer(targetPlayerName);

                if (targetPlayer != null && targetPlayer.isOnline()) {
                    if (!votes.containsKey(player)) {
                        votes.put(player, 1);
                        player.sendMessage(ChatColor.GREEN + "투표가 접수되었습니다.");
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "이미 투표하셨습니다.");
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "플레이어를 찾을 수 없거나 온라인 상태가 아닙니다.");
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "플레이어 닉네임을 입력하세요.");
            }
        }
    }

    public void startVoting() {
        votingEnabled = true;
        votes.clear();
        Bukkit.broadcastMessage(ChatColor.YELLOW + "투표가 시작되었습니다. 최후의 변론 후 살릴지 죽일지를 찬반투표로 결정합니다.");
        Bukkit.getScheduler().runTaskLater(this, () -> endVoting(), 100 * 20L); // 100초 후 투표 종료
    }

    public void endVoting() {
        votingEnabled = false;
        Player maxVotesPlayer = null;
        int maxVotes = 0;

        for (Player player : votes.keySet()) {
            int playerVotes = votes.get(player);
            if (playerVotes > maxVotes) {
                maxVotes = playerVotes;
                maxVotesPlayer = player;
            }
        }

        if (maxVotesPlayer != null) {
            Bukkit.broadcastMessage(ChatColor.YELLOW + "최후의 변론 후 살릴지 죽일지를 결정합니다. 최다 표를 받은 플레이어: " + maxVotesPlayer.getName());
            // 찬반 투표 로직을 구현하고 결과에 따라 살릴지 죽일지를 결정하는 부분을 추가해야합니다.
            // 플레이어 객체인 maxVotesPlayer를 사용하여 처리할 수 있습니다.
        } else {
            Bukkit.broadcastMessage(ChatColor.YELLOW + "최후의 변론 후 살릴지 죽일지를 결정합니다. 최다 표를 받은 플레이어가 없습니다.");
        }
    }

    @EventHandler
    public void Death(PlayerDeathEvent event) {
        if (mafiaTime) {
            mafiaTime = false;
        } else {
            mafiaTime = true;
        }
    }

    public void MafiaTeleport(Player player) {
    }
}
