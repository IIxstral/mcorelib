package de.timc.mcorelib.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.timc.mcorelib.core.MCore;
import de.timc.mcorelib.plugin.MCorePlayer;

public class PlayerMoveListener extends ListenerProperty{
	
	protected PlayerMoveListener(JavaPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		MCorePlayer p = MCore.get().getMCorePlayer(e.getPlayer().getName());
		e.setCancelled(!p.isMovingAllowed());
	}
	
}
