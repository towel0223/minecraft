package as.mafiaplugin;

<<<<<<< HEAD
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Han implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("커맨드 수행!");
            Location eyeLocation = player.getEyeLocation();
            Block targetBlock = player.getTargetBlock(null, 5);


            if (targetBlock.getType() != Material.AIR) {
                // 플레이어의 인벤토리를 얻어옵니다.
                Inventory inventory = player.getInventory();
                // 블럭을 아이템으로 변환하여 인벤토리에 추가합니다.
                ItemStack blockItem = new ItemStack(targetBlock.getType());
                inventory.addItem(blockItem);

                Block block = eyeLocation.getBlock();
                block.setType(Material.AIR);

                player.sendMessage("블럭을 인벤토리에 추가하였습니다.");
                return true;
            } else {
                player.sendMessage("커서가 가리키는 곳에 블럭이 없습니다.");
            }
        }
        return true;
    }
}
