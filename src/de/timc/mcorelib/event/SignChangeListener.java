package de.timc.mcorelib.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.timc.mcorelib.core.MCore;
import de.timc.mcorelib.plugin.MCorePlayer;

public class SignChangeListener extends ListenerProperty {

	protected SignChangeListener(JavaPlugin plugin) {
		super(plugin);
	}

	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		MCorePlayer p = MCore.get().getMCorePlayer(e.getPlayer().getName());
		if (p.isColorSignWriteAllowed()) {
			for (int i = 0; i < e.getLines().length; i++) {
				e.setLine(i, e.getLine(i).replaceAll("&", "§"));
			}
		}
	}

}
