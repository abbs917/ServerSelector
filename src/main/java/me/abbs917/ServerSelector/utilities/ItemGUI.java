package me.abbs917.ServerSelector.utilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.clip.placeholderapi.PlaceholderAPI;

public class ItemGUI {

	private String displayName, serverName;
	private int slot, data;
	private Material material;
	private List<String> lore, consoleCommands, playerCommands;
	private boolean glow;
	
	public ItemGUI(String displayName, String serverName, int slot, int data, Material material, List<String> lore,
			List<String> consoleCommands, List<String> playerCommands, boolean glow)
	{
	    this.displayName = displayName;
	    this.serverName = serverName;
	    this.slot = slot;
	    this.data = data;
	    this.material = material;
	    this.lore = lore;
	    this.consoleCommands = consoleCommands;
	    this.playerCommands = playerCommands;
	    this.glow = glow;
	}

	public ItemStack toItemStack() {
		boolean papiStatus = Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null;
		@SuppressWarnings("deprecation")
		ItemStack item = new ItemStack(this.material, 1, (short) this.data);
		ItemMeta im = item.getItemMeta();
		if (this.glow) {
			item.addEnchantment(Enchantment.DURABILITY, 0);
			im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (papiStatus) {
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
					PlaceholderAPI.setPlaceholders(null, this.displayName)));
		} else {
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.displayName));			
		}
		
		List<String> readyLore = new ArrayList<String>();
		for(String line : this.lore)
		{
			if (papiStatus) {
				readyLore.add(ChatColor.translateAlternateColorCodes('&',
						PlaceholderAPI.setPlaceholders(null, line)));				
			} else {
				readyLore.add(ChatColor.translateAlternateColorCodes('&', line));
			}
			
		}
		im.setLore(readyLore);
		item.setItemMeta(im);
		return item;
	}
	
	public boolean compare(ItemStack item, int slot) {
		return slot == this.slot
				&& item.getType().equals(this.material)
				&& ChatColor.stripColor(item.getItemMeta().getDisplayName()).equals(
				ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', this.displayName)));
	}
	
	public List<String> getConsoleCommands() {
		return this.consoleCommands; 
	}
	
	public List<String> getPlayerCommands() {
		return this.playerCommands; 
	}
	
	public int getSlot() {
		return this.slot;
	}

	public String getServerName() {
		return serverName;
	}
}
