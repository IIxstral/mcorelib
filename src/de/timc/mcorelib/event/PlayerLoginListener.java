package de.timc.mcorelib.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.plugin.java.JavaPlugin;

import de.timc.mcorelib.core.MCore;

public class PlayerLoginListener extends ListenerProperty{

	protected PlayerLoginListener(JavaPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onJoin(AsyncPlayerPreLoginEvent e){
		if(!MCore.get().isLoginAllowed()){
			e.disallow(Result.KICK_OTHER, MCore.header + "Du hast keine Berechtigung, den Server zu betreten.");
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		if(MCore.get().isUpdateFound()){
			e.getPlayer().sendMessage(MCore.header + "§eFor the MCore library is a new version available. More information in your console.");
		}
	}
	
}
