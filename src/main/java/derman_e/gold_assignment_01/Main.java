package derman_e.gold_assignment_01;

import derman_e.gold_assignment_01.commands.GA_Commands;
import derman_e.gold_assignment_01.events.GA_Events;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class main extends JavaPlugin implements Listener {
    public final Logger logger = Logger.getLogger("Minecraft");
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
        GA_Commands cmd_upgrade = new GA_Commands(this, "upgrade");
        GA_Commands cmd_helpme = new GA_Commands(this, "helpme");

        Objects.requireNonNull(getCommand(cmd_upgrade.getLabel())).setExecutor(cmd_upgrade);
        Objects.requireNonNull(getCommand(cmd_upgrade.getLabel())).setTabCompleter(cmd_upgrade);
        console(ChatColor.YELLOW + "/upgrade" + ChatColor.WHITE + " has been added!");

        Objects.requireNonNull(getCommand(cmd_helpme.getLabel())).setExecutor(cmd_helpme);
        Objects.requireNonNull(getCommand(cmd_helpme.getLabel())).setTabCompleter(cmd_helpme);
        console(ChatColor.YELLOW + "/helpme" + ChatColor.WHITE + " has been added!");

        pManager.registerEvents(new GA_Events(this), this);
        console(ChatColor.WHITE + "Jump Upgrades" + ChatColor.WHITE + " have been added!");

        main.console(ChatColor.GOLD + pfName + ChatColor.WHITE + " is now ENABLED!");

        super.onEnable();

    }
    @Override
    public void onDisable() {
        main.console(ChatColor.GOLD + pfName + ChatColor.WHITE + " is now DISABLED!");
        super.onDisable();
    }

}
