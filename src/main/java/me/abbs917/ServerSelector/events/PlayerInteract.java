package me.abbs917.ServerSelector.events;

import me.abbs917.ServerSelector.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

	private Main plugin;
	
	public PlayerInteract(Main plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (player.getInventory().getItemInHand().equals(plugin.getGUIItem().toItemStack()))
				player.openInventory(plugin.getGUI());
		}
	}
	
}
