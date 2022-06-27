package derman_e.gold_assignment_01.commands;

import derman_e.gold_assignment_01.Gold_Assignment_01;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GA_Commands extends AbstractCommand {
    public GA_Commands(Gold_Assignment_01 plugin, String commandLabel) {
        super(plugin, commandLabel);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs = new ArrayList<>();

        if (args.length == 1) {
            tabs.add("help");
            tabs.add("info");
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
                    if (args[0].equalsIgnoreCase("help")) {
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GRAY + "=====================================================");
                        player.sendMessage("");
                        player.sendMessage(ChatColor.AQUA + ga01.getFullName() + ChatColor.WHITE + " 플러그인에 포함된 명령어입니다.");
                        player.sendMessage(ChatColor.GREEN + "/ga help" + ChatColor.WHITE + " : 이 플러그인의 도움말을 출력합니다.");
                        player.sendMessage(ChatColor.GREEN + "/ga info" + ChatColor.WHITE + " : 이 플러그인의 정보를 출력합니다.");
                        player.sendMessage(ChatColor.GREEN + "/ga upgrade" + ChatColor.WHITE + " : 다이아몬드 검을 들고 실행하면 날카로움 0/1/2/3/4 에서 100%/80%/60%/40%/20% 확률로 한 등급 올라갑니다.");
                        player.sendMessage(ChatColor.GREEN + "/ga helpme" + ChatColor.WHITE + " : 눈 앞에 철 골렘 한 마리와 네더라이트 흉갑을 소환합니다.");
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GRAY + "=====================================================");
                        player.sendMessage("");
                    } else if (args[0].equalsIgnoreCase("info")) {
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GRAY + "=====================================================");
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GOLD + " - 플러그인 이름" + ChatColor.WHITE + " : " + ga01.getPdfFile().getName());
                        player.sendMessage(ChatColor.GOLD + " - 플러그인 버전" + ChatColor.WHITE + " : " + ga01.getPdfFile().getVersion());
                        player.sendMessage(ChatColor.GOLD + " - 플러그인 만든 놈" + ChatColor.WHITE + " : " + ga01.getPdfFile().getAuthors());
                        player.sendMessage(ChatColor.GOLD + " - 플러그인 메인 클래스" + ChatColor.WHITE + " : " + ga01.getPdfFile().getMain());
                        player.sendMessage(ChatColor.GOLD + " - 설명" + ChatColor.WHITE + " : " + ga01.getPdfFile().getDescription());
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GRAY + "=====================================================");
                        player.sendMessage("");
                    } else if (args[0].equalsIgnoreCase("upgrade")) {
                        /* if (손에 다이아몬드 검이 없으면) {
                        *    player.sendMessage(ChatColor.RED + "주로 사용하는 손에 다이아몬드 검을 들고 있어야 합니다!");
                        *    break;
                        * } else {
                        *    int random = (int)(Math.random() * 100 + 1);
                        *
                        *    if (손에 다이아몬드 검이 날카로움이 없는 상태라면) {
                        *        날카로움 + I 또는 날카로움 I로 설정
                        *        player.sendMessage(ChatColor.GREEN + "100% 강화에 성공했습니다!");
                        *    } else if (손에 다이아몬드 검이 날카로움 I인 상태라면) {
                        *        if (random <= 80) {
                        *            날카로움 + I 또는 날카로움 II로 설정
                        *            player.sendMessage(ChatColor.GREEN + "80% 강화에 성공했습니다!");
                        *        } else {
                        *            player.sendMessage(ChatColor.GREEN + "80% 강화에 실패했습니다...");
                        *        }
                        *    } else if (손에 다이아몬드 검이 날카로움 II인 상태라면) {
                        *        if (random <= 60) {
                        *            날카로움 + I 또는 날카로움 III로 설정
                        *            player.sendMessage(ChatColor.GREEN + "60% 강화에 성공했습니다!");
                        *        } else {
                        *            player.sendMessage(ChatColor.GREEN + "60% 강화에 실패했습니다...");
                        *        }
                        *    } else if (손에 다이아몬드 검이 날카로움 III인 상태라면) {
                        *        if (random <= 40) {
                        *            날카로움 + I 또는 날카로움 IV로 설정
                        *            player.sendMessage(ChatColor.GREEN + "40% 강화에 성공했습니다!");
                        *        } else {
                        *            player.sendMessage(ChatColor.GREEN + "40% 강화에 실패했습니다...");
                        *        }
                        *    } else if (손에 다이아몬드 검이 날카로움 IV인 상태라면) {
                        *        if (random <= 20) {
                        *            날카로움 + I 또는 날카로움 V로 설정
                        *            player.sendMessage(ChatColor.GREEN + "20% 강화에 성공했습니다!");
                        *        } else {
                        *            player.sendMessage(ChatColor.GREEN + "20% 강화에 실패했습니다...");
                        *        }
                        *    }
                        * }
                        */
                    } else if (args[0].equalsIgnoreCase("helpme")) {
                        /* 철골렘 엔티티 소환
                         * 네더라이트 흉갑 아이템 엔티티 소환
                         * player.sendMessage(ChatColor.WHITE + "당신을 도와주기 위해 " + ChatColor.GOLD + "아이언맨" + ChatColor.WHITE + "이 나타났습니다!");
                         */
                    }
                }
            }
        }

        return false;
    }
}
