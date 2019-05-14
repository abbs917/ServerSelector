package me.abbs917.ServerSelector.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpWorldCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 5) {
			Player player = Bukkit.getServer().getPlayer(args[0]);
			if (player != null && player.isOnline()) {
				World world = Bukkit.getServer().getWorld(args[1]);
				if (world != null) {
					float x, y, z;
					try {
						x = Float.valueOf(args[2]);
						y = Float.valueOf(args[3]);
						z = Float.valueOf(args[4]);
						player.teleport(new Location(world, x, y, z));
						sender.sendMessage("§aTarget player has been teleported successfuly");
					} catch (Exception e) {
						sender.sendMessage("§cX, Y and Z must be numbers");
					}
				} else {
					sender.sendMessage("§cCouldn't find target world");
				}
			} else {
				sender.sendMessage("§cCouldn't find target player");
			}
		} else {
			sender.sendMessage("/tpworld <player> <world> <x> <y> <z>");
		}
		return true;
	}
	
}
