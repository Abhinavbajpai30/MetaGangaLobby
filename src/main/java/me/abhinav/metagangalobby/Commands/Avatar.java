package me.abhinav.metagangalobby.Commands;

import me.abhinav.metagangalobby.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Avatar implements CommandExecutor {
    Main main = Main.getInstance();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You need to be a player to execute this command!");
            return false;
        }
        Player player = (Player) sender;
        if(args.length!=1) {
            player.sendMessage(ChatColor.RED + "Format: /avatar (1 - 6)");
            return false;
        }
        int skin;
        try {
            skin = Integer.parseInt(args[0]);
        } catch(NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Format: /avatar (1 - 6)");
            return false;
        }
        if(skin<1 || skin>6) {
            player.sendMessage(ChatColor.RED + "Format: /avatar (1 - 6)");
            return false;
        }
        main.applySkin(player, skin);
        player.sendMessage(ChatColor.GREEN + "Your avatar has been changed!");
        return true;
    }
}
