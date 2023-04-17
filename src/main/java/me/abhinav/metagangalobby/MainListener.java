package me.abhinav.metagangalobby;

import me.lagbug.emailer.spigot.api.events.PlayerVerifyEvent;
import net.skinsrestorer.api.SkinsRestorerAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class MainListener implements Listener {
    Main main = Main.getInstance();

    @EventHandler
    public void onPlayerVerify(PlayerVerifyEvent e) {
        Player player = e.getPlayer();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + e.getPlayer().getName() + " parent add verified");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.setGameMode(GameMode.ADVENTURE);
        player.setFlying(false);
        player.setExp(0);
        player.setLevel(0);

        e.setJoinMessage(null);

        if(!SkinsRestorerAPI.getApi().hasSkin(player.getName())) {
            int ramdomSKin = (int) Math.floor(Math.random() * 6 + 1);
            main.applySkin(player, ramdomSKin);
        }

        player.sendTitle(ChatColor.AQUA + "Welcome to Gift of Ganga", ChatColor.GREEN + "Enjoy your stay!");
        player.sendMessage(ChatColor.GREEN + "Welcome to Gift of Ganga. Enjoy your stay!");
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);

        new BukkitRunnable(){
            @Override
            public void run() {
                if(!player.hasPermission("email.verified")) {
                    player.sendMessage(ChatColor.RED + "Please connect your Email to play!");
                    player.sendMessage(ChatColor.GREEN + "Use - /setemail [email-address]");
                    player.playSound(player, Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                }
            }
        }.runTaskLater(main, 100L);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent e) {
        if(e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.COMMAND) || e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM) || e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.EGG)) {
            return;
        }
        e.setCancelled(true);
    }
}
