package de.timc.mcorelib.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import de.timc.mcorelib.core.MCore;
import de.timc.mcorelib.plugin.MCoreLibPlugin;
import de.timc.mcorelib.plugin.MCorePlayer;

public class BlockPlaceListener extends ListenerProperty {

	protected BlockPlaceListener(MCoreLibPlugin plugin) {
		super(plugin);
	}

	@EventHandler
	public void onBlockBreak(BlockPlaceEvent event) {
		MCorePlayer p = MCore.get().getMCorePlayer(event.getPlayer().getName());
		if (p.isBlockPlaceDisabled()) {
			event.setCancelled(!p.getBlockPlaceWhitelist().contains(event.getBlock().getType()));
		}
	}
}
