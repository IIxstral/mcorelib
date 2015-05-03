package de.timc.mcorelib.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.timc.mcorelib.core.MCore;
import de.timc.mcorelib.plugin.MCorePlayer;

public class BlockBreakListener extends ListenerProperty {

	protected BlockBreakListener(JavaPlugin plugin) {
		super(plugin);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		MCorePlayer p = MCore.get().getMCorePlayer(event.getPlayer().getName());
		if (p.isBlockBreakDisabled()) {
			event.setCancelled(!p.getBlockBreakWhitelist().contains(event.getBlock().getType()));
		}
	}

}
