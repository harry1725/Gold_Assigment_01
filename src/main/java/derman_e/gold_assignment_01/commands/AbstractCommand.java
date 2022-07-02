package derman_e.gold_assignment_01.commands;

import derman_e.gold_assignment_01.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public abstract class AbstractCommand implements TabExecutor {
    protected Main ga01;
    private final String label;

    public AbstractCommand(Main plugin, String commandLabel) {
        this.ga01 = plugin;
        this.label = commandLabel;
    }

    public String getLabel() {
        return label;
    }

    public abstract List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args);
    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
