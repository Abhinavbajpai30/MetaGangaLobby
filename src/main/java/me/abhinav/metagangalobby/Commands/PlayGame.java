package me.abhinav.metagangalobby.Commands;

import me.abhinav.metagangalobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayGame implements CommandExecutor {
    Main main = Main.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        if(args.length==0) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You need to be a player to execute this command!");
                return false;
            }
            player = (Player) sender;
        } else {
            player = Bukkit.getPlayerExact(args[0]);
            if(player==null) {
                sender.sendMessage(ChatColor.RED + "Player not found!");
                return false;
            }
        }

        if(!player.hasPermission("email.verified")) {
            player.sendMessage(ChatColor.RED + "Please Connect Your Email to play Meta Ganga!");
            player.sendMessage(ChatColor.GREEN + "Use - /setemail [email-address]");
            player.sendTitle(ChatColor.RED + "Please Connect your Email!", ChatColor.GREEN + "Use - /setemail [email-address]");
            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            return false;
        }
        if(!player.hasPermission("metaganga.admin") && player.hasPermission("metaganga.finished")) {
            player.sendMessage(ChatColor.RED + "You have already played and made your contribution!");
            player.sendTitle(ChatColor.RED + "You have already made your contribution!", ChatColor.AQUA + "You can check your score on Leaderboard");
            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            return false;
        }
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try{
            dataOutputStream.writeUTF("Connect");
            dataOutputStream.writeUTF("arena");
        } catch(IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(Main.getInstance(), "BungeeCord", byteArrayOutputStream.toByteArray());

        return true;
    }
}
