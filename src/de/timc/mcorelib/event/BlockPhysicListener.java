package de.timc.mcorelib.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.timc.mcorelib.core.MCore;

public class BlockPhysicListener extends ListenerProperty {
	protected BlockPhysicListener(JavaPlugin plugin) {
		super(plugin);
	}

	@EventHandler
	public void onBlockPhysic(BlockPhysicsEvent e){
		e.setCancelled(!MCore.get().isBlockPhysicsAllowed());
	}
}
