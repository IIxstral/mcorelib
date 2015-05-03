package de.timc.mcorelib.event;

import de.timc.mcorelib.plugin.MCoreLibPlugin;

public class MCoreListener {

	private MCoreLibPlugin plugin;
	private ChatProperty cproperty;

	
	public MCoreListener(MCoreLibPlugin plugin){
		this.plugin = plugin;
		this.cproperty = new ChatProperty(plugin);
		new BlockBreakListener(plugin);
		new BlockPlaceListener(plugin);
		new GameModeChangeListener(plugin);
		new InventoryClickListener(plugin);
		new PlayerDropItemListener(plugin);
		new PlayerLoginListener(plugin);
		new PlayerPickupItemListener(plugin);
		new SignChangeListener(plugin);
		new DamageListener(plugin);
		new PlayerMoveListener(plugin);
		new BlockPhysicListener(plugin);
	}
	public ChatProperty getChatProperty() {
		return cproperty;
	}
	public MCoreLibPlugin getPlugin() {
		return plugin;
	}
	
	
	
}
