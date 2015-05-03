package de.timc.mcorelib.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.timc.mcorelib.core.MCore;
import de.timc.mcorelib.plugin.MCorePlayer;

public class DamageListener extends ListenerProperty {

	protected DamageListener(JavaPlugin plugin) {
		super(plugin);
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			MCorePlayer p = MCore.get().getMCorePlayer(((Player) e.getDamager()).getName());
			if (!p.isPlayerDamageAllowed() || !p.isDamageAllowed()) {
				if (e.getEntity() instanceof Player) {
					e.setCancelled(!p.isPlayerDamageAllowed() ? p.getDamagePlayerWhitelist().contains(((Player) e.getEntity()).getName()) : false);
				} else {
					e.setCancelled(!p.isDamageAllowed());
				}
			}
			
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player){
			MCorePlayer p = MCore.get().getMCorePlayer(((Player) e.getEntity()).getName());
			e.setCancelled(!p.isOwnDamageAllowed());
		}
	}

}
