package derman_e.gold_assignment_01;

import derman_e.gold_assignment_01.commands.GA_Commands;
import derman_e.gold_assignment_01.items.GAItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin implements Listener {
    PluginDescriptionFile pdfFile = this.getDescription();
    PluginManager pManager = Bukkit.getPluginManager();

    String pfName = pdfFile.getName() + " v" + pdfFile.getVersion();

    public PluginDescriptionFile getPdfFile() {
        return pdfFile;
    }

    public String getFullName() {
        return pfName;
    }

    public static void console(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    @Override
    public void onEnable() {
        GA_Commands cmd_ga01 = new GA_Commands(this, "ga01");

        Objects.requireNonNull(getCommand(cmd_ga01.getLabel())).setExecutor(cmd_ga01);
        Objects.requireNonNull(getCommand(cmd_ga01.getLabel())).setTabCompleter(cmd_ga01);

        pManager.registerEvents(this, this);

        Main.console(ChatColor.GOLD + pfName + ChatColor.WHITE + " is now ENABLED!");

        super.onEnable();

    }

    @Override
    public void onDisable() {
        Main.console(ChatColor.GOLD + pfName + ChatColor.WHITE + " is now DISABLED!");
        super.onDisable();
    }

    /* @EventHandler
     *
     */
}
