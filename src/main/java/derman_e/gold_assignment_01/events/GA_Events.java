package derman_e.gold_assignment_01.events;

import derman_e.gold_assignment_01.main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.logging.Logger;

public class GA_Events implements Listener {
    public final Logger logger = Logger.getLogger("Minecraft");
    public static main Main;

    public GA_Events(main plugin) {
        Main = plugin;
    }

    public void scBoard(Player player, int sc) {
        ScoreboardManager sm = Bukkit.getScoreboardManager();

        Scoreboard board = Objects.requireNonNull(sm).getNewScoreboard();
        Objective obj = board.registerNewObjective("doubleJump", "dummy", "doubleJump");
        Score score = obj.getScore(player.getName());

        score.setScore(sc);
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        PotionEffect jumpBoost = new PotionEffect(PotionEffectType.JUMP, 25, 1, true, true);
        final int[] time = {0};

        if (!player.isSneaking()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (time[0] < 20) {
                        time[0]++;

                        if (!player.isSneaking()) {
                            time[0] = 0;
                            cancel();
                        }
                    } else {
                        time[0] = 0;
                        player.addPotionEffect(jumpBoost);
                    }
                }
            }.runTaskTimer(Main, 0L, 1L);
        } else {
            player.removePotionEffect(PotionEffectType.JUMP);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setAllowFlight(true);
    }

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        Vector direction = player.getLocation().getDirection();
        GameMode gamemode = player.getGameMode();

        if ((gamemode == GameMode.SURVIVAL || gamemode == GameMode.ADVENTURE) && !player.isFlying()) {
            event.setCancelled(true);
            scBoard(player, 1);

            player.setAllowFlight(false);
            player.setFlying(false);

            player.setVelocity(direction.multiply(0.5).setY(0.5));
        }
    }

    @EventHandler
    public void onHitGround(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block block = Objects.requireNonNull(event.getTo()).getBlock();

        if (((Entity)player).isOnGround() || block.isLiquid()) {
            scBoard(player, 0);
            player.setFlying(false);
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        GameMode gamemode = player.getGameMode();

        if (gamemode == GameMode.SURVIVAL || gamemode == GameMode.ADVENTURE) {
            player.setFlying(false);
            player.setFlySpeed(0);
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        scBoard(player, 0);
    }

}
