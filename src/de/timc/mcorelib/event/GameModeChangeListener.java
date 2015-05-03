package de.timc.mcorelib.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.timc.mcorelib.core.MCore;
import de.timc.mcorelib.plugin.MCorePlayer;

public class GameModeChangeListener extends ListenerProperty{

	protected GameModeChangeListener(JavaPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onGamemodeChange(PlayerGameModeChangeEvent e){
		MCorePlayer p = MCore.get().getMCorePlayer(e.getPlayer().getName());
		e.setCancelled(!p.isGameModeChangeAllowed());
	}
}
