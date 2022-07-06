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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class GA_Commands extends AbstractCommand {
    public final Logger logger = Logger.getLogger("Minecraft");

    public GA_Commands(main plugin, String commandLabel) {
        super(plugin, commandLabel);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs = new ArrayList<>();

        if (args.length == 1) {
            tabs.add("upgrade");
            tabs.add("helpme");
        }

        return tabs;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[]args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (label.equalsIgnoreCase("ga")) {
                if (args.length <= 0) {
                    player.sendMessage(ChatColor.RED + "명령어의 인자가 너무 적거나 없습니다! "+ ChatColor.YELLOW + "/ga help" + ChatColor.RED + " 명령어를 통해 도움말을 확인할 수 있습니다.");
                } else {
                    ItemStack mainHandItemStack = player.getInventory().getItemInMainHand();
                    ItemMeta mainHandItemMeta = mainHandItemStack.getItemMeta();

                    if (args[0].equalsIgnoreCase("upgrade")) {
                        if (!mainHandItemStack.equals(new ItemStack(Material.DIAMOND_SWORD))) {
                            player.sendMessage(ChatColor.RED + "주로 사용하는 손에 다이아몬드 검을 들고 있어야 합니다!");
                        } else {
                            int random = (int)(Math.random() * 100 + 1);

                            if (Objects.requireNonNull(mainHandItemMeta).getEnchantLevel(Enchantment.DAMAGE_ALL) == 0) {
                                mainHandItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
                                player.sendMessage(ChatColor.AQUA + "100% 강화에 " + ChatColor.GREEN + "성공" + ChatColor.AQUA + "했습니다!");
                            } else if (mainHandItemMeta.getEnchantLevel(Enchantment.DAMAGE_ALL) == 1) {
                                if (random <= 80) {
                                    mainHandItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
                                    player.sendMessage(ChatColor.AQUA + "80% 강화에 " + ChatColor.GREEN + "성공" + ChatColor.AQUA + "했습니다!");
                                } else {
                                    player.sendMessage(ChatColor.YELLOW + "80% 강화에 " + ChatColor.RED + "실패" + ChatColor.YELLOW + "했습니다...");
                                }
                            } else if (mainHandItemMeta.getEnchantLevel(Enchantment.DAMAGE_ALL) == 2) {
                                if (random <= 60) {
                                    mainHandItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
                                    player.sendMessage(ChatColor.AQUA + "60% 강화에 " + ChatColor.GREEN + "성공" + ChatColor.AQUA + "했습니다!");
                                } else {
                                    player.sendMessage(ChatColor.YELLOW + "60% 강화에 " + ChatColor.RED + "실패" + ChatColor.YELLOW + "했습니다...");
                                }
                            } else if (mainHandItemMeta.getEnchantLevel(Enchantment.DAMAGE_ALL) == 3) {
                                if (random <= 40) {
                                    mainHandItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
                                    player.sendMessage(ChatColor.AQUA + "40% 강화에 " + ChatColor.GREEN + "성공" + ChatColor.AQUA + "했습니다!");
                                } else {
                                    player.sendMessage(ChatColor.YELLOW + "40% 강화에 " + ChatColor.RED + "실패" + ChatColor.YELLOW + "했습니다...");
                                }
                            } else if (mainHandItemMeta.getEnchantLevel(Enchantment.DAMAGE_ALL) == 4) {
                                if (random <= 20) {
                                    mainHandItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
                                    player.sendMessage(ChatColor.AQUA + "20% 강화에 " + ChatColor.GREEN + "성공" + ChatColor.AQUA + "했습니다!");
                                } else {
                                    player.sendMessage(ChatColor.YELLOW + "20% 강화에 " + ChatColor.RED + "실패" + ChatColor.YELLOW + "했습니다...");
                                }
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("helpme")) {
                        World world = player.getWorld();
                        Location location = player.getLocation();

                        world.spawnEntity(location, EntityType.IRON_GOLEM);
                        world.dropItem(location, new ItemStack(Material.NETHERITE_CHESTPLATE));

                        player.sendMessage(ChatColor.WHITE + "당신을 도와주기 위해 " + ChatColor.GOLD + "아이언맨" + ChatColor.WHITE + "이 " + ChatColor.GRAY + "여분의 슈트" + ChatColor.WHITE + "를 가지고 나타났습니다!");
                    }
                }
            }
        }

        return false;
    }
}
