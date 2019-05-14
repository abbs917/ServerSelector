package me.abbs917.ServerSelector.commands;

import me.abbs917.ServerSelector.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerGUICommand implements CommandExecutor {
	
	private Main plugin;
	
	public ServerGUICommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command");
		}
		Player player = (Player) sender;
		player.openInventory(plugin.getGUI());
		return true;
	}

}
