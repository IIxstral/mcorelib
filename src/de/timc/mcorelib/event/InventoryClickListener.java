package de.timc.mcorelib.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.timc.mcorelib.core.MCore;
import de.timc.mcorelib.plugin.MCoreLibPlugin;
import de.timc.mcorelib.plugin.MCorePlayer;

public class InventoryClickListener extends ListenerProperty {

	protected InventoryClickListener(MCoreLibPlugin plugin) {
		super(plugin);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		MCorePlayer p = MCore.get().getMCorePlayer(e.getWhoClicked().getName());
		e.setCancelled(p.getInventoryNameBlacklist().contains(e.getInventory().getTitle()));
	}
}
