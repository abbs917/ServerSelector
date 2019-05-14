package me.abbs917.ServerSelector.events;

import me.abbs917.ServerSelector.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	private Main plugin;
	
	public PlayerJoin(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		e.getPlayer().getInventory().setItem(plugin.getGUIItem().getSlot(), plugin.getGUIItem().toItemStack());
	}
	
}
