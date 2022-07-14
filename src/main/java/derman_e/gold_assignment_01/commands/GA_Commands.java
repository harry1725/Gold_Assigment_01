package derman_e.gold_assignment_01.commands;

import derman_e.gold_assignment_01.main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GA_Commands extends AbstractCommand {
    public final Logger logger = Logger.getLogger("Minecraft");

    public GA_Commands(main plugin, String commandLabel) {
        super(plugin, commandLabel);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs = new ArrayList<>();

        if (args.length == 0) {
            tabs.add("upgrade");
            tabs.add("helpme");
        }

        return tabs;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[]args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (label.equalsIgnoreCase("upgrade")) {
                ItemStack mainHandIS = player.getInventory().getItemInMainHand();
                int mainHandEnchantLevel = mainHandIS.getEnchantmentLevel(Enchantment.DAMAGE_ALL);

                if (!mainHandIS.getType().equals(Material.DIAMOND_SWORD)) {
                    player.sendMessage(ChatColor.RED + "주로 사용하는 손에 다이아몬드 검을 들고 있어야 합니다!");
                } else {
                    int random = (int)(Math.random() * 100 + 1);

                    switch (mainHandEnchantLevel) {
                        case 0:
                            mainHandIS.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                            player.sendMessage(ChatColor.AQUA + "100% 강화에 " + ChatColor.GREEN + "성공" + ChatColor.AQUA + "했습니다!");

                            break;
                        case 1:
                            if (random <= 80) {
                                mainHandIS.addEnchantment(Enchantment.DAMAGE_ALL, 2);
                                player.sendMessage(ChatColor.AQUA + "80% 강화에 " + ChatColor.GREEN + "성공" + ChatColor.AQUA + "했습니다!");
                            } else {
                                player.sendMessage(ChatColor.YELLOW + "80% 강화에 " + ChatColor.RED + "실패" + ChatColor.YELLOW + "했습니다...");
                            }

                            break;
                        case 2:
                            if (random <= 60) {
                                mainHandIS.addEnchantment(Enchantment.DAMAGE_ALL, 3);
                                player.sendMessage(ChatColor.AQUA + "60% 강화에 " + ChatColor.GREEN + "성공" + ChatColor.AQUA + "했습니다!");
                            } else {
                                player.sendMessage(ChatColor.YELLOW + "60% 강화에 " + ChatColor.RED + "실패" + ChatColor.YELLOW + "했습니다...");
                            }

                            break;
                        case 3:
                            if (random <= 40) {
                                mainHandIS.addEnchantment(Enchantment.DAMAGE_ALL, 4);
                                player.sendMessage(ChatColor.AQUA + "40% 강화에 " + ChatColor.GREEN + "성공" + ChatColor.AQUA + "했습니다!");
                            } else {
                                player.sendMessage(ChatColor.YELLOW + "40% 강화에 " + ChatColor.RED + "실패" + ChatColor.YELLOW + "했습니다...");
                            }

                            break;
                        case 4:
                            if (random <= 20) {
                                mainHandIS.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                                player.sendMessage(ChatColor.AQUA + "20% 강화에 " + ChatColor.GREEN + "성공" + ChatColor.AQUA + "했습니다!");
                            } else {
                                player.sendMessage(ChatColor.YELLOW + "20% 강화에 " + ChatColor.RED + "실패" + ChatColor.YELLOW + "했습니다...");
                            }

                            break;
                        default:
                            player.sendMessage(ChatColor.RED + "강화 불가능한 날카로움 등급입니다. 강화는 I~IV 등급의 날카로움만 가능합니다.");
                    }
                }
            } else if (label.equalsIgnoreCase("helpme")) {
                World world = player.getWorld();
                Location location = player.getLocation();

                world.spawnEntity(location, EntityType.IRON_GOLEM);
                world.dropItem(location, new ItemStack(Material.NETHERITE_CHESTPLATE));

                player.sendMessage(ChatColor.WHITE + "당신을 도와주기 위해 " + ChatColor.GOLD + "아이언 맨" + ChatColor.WHITE + "이 " + ChatColor.GRAY + "여분의 슈트" + ChatColor.WHITE + "를 가지고 나타났습니다!");
            }
        }

        return false;
    }
}
