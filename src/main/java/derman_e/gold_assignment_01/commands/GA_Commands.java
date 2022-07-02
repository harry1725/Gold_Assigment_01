package derman_e.gold_assignment_01.commands;

import derman_e.gold_assignment_01.main;
import derman_e.gold_assignment_01.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GA_Commands extends AbstractCommand {
    public final Logger logger = Logger.getLogger("GA");

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
                    if (args[0].equalsIgnoreCase("upgrade")) {
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
                    } else {

                    }
                }
            }
        }

        return false;
    }
}
