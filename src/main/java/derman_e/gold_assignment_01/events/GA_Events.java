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

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

public class GA_Events implements Listener {
    public final Logger logger = Logger.getLogger("Minecraft");
    public static main Main;

    HashMap<UUID, Integer> time = new HashMap<>();
    HashMap<UUID, Boolean> dj = new HashMap<>();

    public GA_Events(main plugin) {
        Main = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        time.put(uuid, 0);
        player.setAllowFlight(true);
        dj.put(uuid, false);
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PotionEffect jumpBoost = new PotionEffect(PotionEffectType.JUMP, 25, 1, true, true);

        time.put(uuid, 0);

        if (!player.isSneaking()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (time.get(uuid) < 20) {
                        time.put(uuid, time.get(uuid) + 1);

                        if (!player.isSneaking()) {
                            time.put(uuid, 0);
                            cancel();
                        }
                    } else {
                        time.put(uuid, 0);
                        player.addPotionEffect(jumpBoost);
                    }
                }
            }.runTaskTimer(Main, 0L, 1L);
        } else {
            player.removePotionEffect(PotionEffectType.JUMP);
        }
    }

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Vector direction = player.getLocation().getDirection();
        GameMode gamemode = player.getGameMode();

        if ((gamemode == GameMode.SURVIVAL || gamemode == GameMode.ADVENTURE) && !player.isFlying()) {
            event.setCancelled(true);
            dj.put(uuid, true);

            player.setAllowFlight(false);
            player.setFlying(false);

            player.setVelocity(direction.multiply(0.5).setY(0.5));
        }
    }

    @EventHandler
    public void onHitGround(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Block block = Objects.requireNonNull(event.getTo()).getBlock();

        if (((Entity)player).isOnGround() || block.isLiquid()) {
            dj.put(uuid, false);
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
        UUID uuid = player.getUniqueId();

        dj.put(uuid, false);
    }

}
