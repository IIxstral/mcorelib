package de.timc.mcorelib.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerProperty implements Listener{
	private JavaPlugin plugin;
	
	protected ListenerProperty(JavaPlugin plugin){
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}
	
}
