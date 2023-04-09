package me.abhinav.metagangalobby;

import me.abhinav.metagangalobby.Commands.PlayGame;
import me.lagbug.emailer.spigot.api.EmailerAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin {
    private static Main main;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new MainListener(), this);
        Bukkit.getPluginCommand("playgame").setExecutor(new PlayGame());
        main = this;
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");


        EmailerAPI api = new EmailerAPI();
        remindEmail();
    }

    void remindEmail() {
        new BukkitRunnable(){
            @Override
            public void run() {
                for(Player p: Bukkit.getOnlinePlayers()) {
                    if(!p.hasPermission("email.verified")) {
                        p.sendMessage(ChatColor.RED + "Please Connect Your Email to play Meta Ganga!");
                        p.sendMessage(ChatColor.GREEN + "Use - /setemail [email-address]");
                        p.sendTitle(ChatColor.RED + "Please Connect your Email!", ChatColor.GREEN + "Use - /setemail [email-address]");
                        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                    }
                }
            }
        }.runTaskTimer(main, 1200L, 1200L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return main;
    }
}
