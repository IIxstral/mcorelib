package de.timc.mcorelib.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.timc.mcorelib.core.MCore;
import de.timc.mcorelib.plugin.MCorePlayer;

public class PlayerDropItemListener extends ListenerProperty{

	protected PlayerDropItemListener(JavaPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onDropItem(PlayerDropItemEvent e){
		MCorePlayer p = MCore.get().getMCorePlayer(e.getPlayer().getName());
		e.setCancelled(!p.isDropItemAllowed());
	}

}
