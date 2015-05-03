package de.timc.mcorelib.plugin;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.timc.mcorelib.core.MCore;
import de.timc.mcorelib.event.MCoreListener;
import de.timc.mcorelib.metrics.Metrics;

public class MCoreLibPlugin extends JavaPlugin implements Listener {
	private MCoreListener listener;

	@Override
	public void onEnable() {
		MCore.get().setPluginInstance(this);
		this.listener = new MCoreListener(this);
		Bukkit.getPluginManager().registerEvents(this, this);
		Metrics me;
		try {
			me = new Metrics(this);
			me.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			MCore.get().getMCorePlayer(p.getName()).setOnline(true);
		}
	}

	public MCoreListener getListener() {
		return listener;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		MCore.get().getMCorePlayer(e.getPlayer().getName()).setOnline(true);
		e.setJoinMessage(MCore.get().getJoinMessage().replace("%u", e.getPlayer().getName()));
	}

	@EventHandler
	public void onDiconnect(PlayerQuitEvent e) {
		MCore.get().getMCorePlayer(e.getPlayer().getName()).setOnline(false);
		e.setQuitMessage(MCore.get().getLeaveMessage().replace("%u", e.getPlayer().getName()));
	}
}
