package me.abbs917.ServerSelector;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.abbs917.ServerSelector.commands.ServerGUICommand;
import me.abbs917.ServerSelector.commands.TpWorldCommand;
import me.tkuiyeager1.ServerSelector.events.InventoryClick;
import me.abbs917.ServerSelector.events.PlayerInteract;
import me.abbs917.ServerSelector.events.PlayerJoin;
import me.abbs917.ServerSelector.utilities.ItemGUI;

public class Main extends JavaPlugin {

	private Inventory gui;
	private List<ItemGUI> items;
	private ItemGUI guiItem;
	
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();

		setupGUIItem();
		setupInventory();
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		registerEvents();
		registerCommands();
	}

	private void setupGUIItem() {
		this.guiItem = new ItemGUI(getConfig().getString("selector_item.display_name"),
				"",
				getConfig().getInt("selector_item.slot"),
				getConfig().getInt("selector_item.data"),
				Material.getMaterial(getConfig().getString("selector_item.item")),
				getConfig().getStringList("selector_item.lore"),
				new ArrayList<String>(), new ArrayList<String>(), getConfig().getBoolean("selector_item.glow"));
	}

	private void setupInventory() {
		items = new ArrayList<ItemGUI>();
		int rows = getConfig().getInt("rows");
		String displayname = getConfig().getString("selector_name");
		gui = Bukkit.createInventory(null, rows * 9, ChatColor.translateAlternateColorCodes('&', displayname));
		ItemGUI rest = null;
		for (String key : getConfig().getConfigurationSection("slots").getKeys(false)) {
			String path = "slots." + key;
			String displayName = getConfig().getString(path + ".display_name");
			String serverName = getConfig().getString(path + ".server_name");
			int slot = Integer.valueOf(key);
			int data = getConfig().getInt(path + ".data");
			Material material = Material.getMaterial(getConfig().getString(path + ".item"));
			List<String> lore = getConfig().getStringList(path + ".lore");
			List<String> consoleCommands = getConfig().getStringList(path + ".console_commands");
			List<String> playerCommands = getConfig().getStringList(path + ".player_commands");
			boolean glow = getConfig().getBoolean(path + ".glow");
			ItemGUI current = new ItemGUI(displayName, serverName, slot,
					data, material, lore, consoleCommands, playerCommands, glow);
			if (slot >= 0) {
				items.add(current);
				this.gui.setItem(slot, current.toItemStack());
			}
			else if (slot == -1)
				rest = current;
		}
		if (rest != null)
			for (int i = 0; i < this.gui.getSize(); i++) {
				if (this.gui.getItem(i) == null)
					this.gui.setItem(i, rest.toItemStack());
			}
	}

	private void registerCommands() {
		getCommand("servergui").setExecutor(new ServerGUICommand(this));
		getCommand("tpworld").setExecutor(new TpWorldCommand());
	}

	private void registerEvents() {
		PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		pluginManager.registerEvents(new InventoryClick(this), this);
		pluginManager.registerEvents(new PlayerInteract(this), this);
		pluginManager.registerEvents(new PlayerJoin(this), this);
	}
	
	public Inventory getGUI() {
		return this.gui;
	}
	
	public List<ItemGUI> getItems() {
		return this.items;
	}
	
	public ItemGUI getGUIItem() {
		return this.guiItem;
	}
}
