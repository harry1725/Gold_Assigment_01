package derman_e.gold_assignment_01.events;

import derman_e.gold_assignment_01.main;
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
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class GA_Events implements Listener {
    public final Logger logger = Logger.getLogger("Minecraft");
    public static main Main;
    private final List<Player> doubleJump = new ArrayList<>();

    public GA_Events(main plugin) {
        Main = plugin;
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        final int[] time = {-1};

        if (player.isSneaking()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    time[0]++;

                    if (time[0] >= 19) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 0, false, true));
                    }
                }
            }.runTaskTimer(Main, 0L, 1L);
        } else {
            time[0] = 0;
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
            doubleJump.add(player);

            player.setAllowFlight(false);
            player.setFlying(false);

            player.setVelocity(direction.multiply(1.5).setY(1.5));
        }
    }

    @EventHandler
    public void onHitGround(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block block = Objects.requireNonNull(event.getTo()).getBlock();

        if ((((Entity)player).isOnGround() || block.isLiquid()) && doubleJump.remove(player)) {
            doubleJump.remove(player);
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        doubleJump.remove(player);
    }

}
