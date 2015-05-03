package de.timc.mcorelib.hologram;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.WitherSkull;
import org.bukkit.util.Vector;

public class Hologram {

	
	/*
	 * Sehr buggy
	 */
	public static synchronized void spawnHologram(ArrayList<String> lore, Location location) {
		try {
			double abstand = 0;
			for (String t : lore) {
				// TODO Entity is already tracked ?? Lösung finden. Ist bereits
				// ein Entity an der gleichen Stelle vorher gespawnt?
				WitherSkull w = (WitherSkull) location.getWorld().spawnEntity(location.clone().add(0, 55 + -abstand, 0), EntityType.WITHER_SKULL);
				w.setDirection(new Vector(0, 0, 0));
				Horse h = (Horse) location.getWorld().spawnEntity(location.clone().add(0, 55 + -abstand, 0), EntityType.HORSE);

				w.setPassenger(h);
				h.setTamed(true);
				h.setAge(-1700000);
				h.setCustomName(t.replaceAll("&", "§"));
				h.setCustomNameVisible(true);
				w.teleport(new Location(w.getWorld(), w.getLocation().getX(), -999999999999999.0, w.getLocation().getZ()));
				abstand += 0.24;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
}
