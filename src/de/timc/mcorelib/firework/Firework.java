package de.timc.mcorelib.firework;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

public class Firework {

	private int power;
	private Location location;
	private ArrayList<MFireEffect> effects;
	private Vector velocity;
	private org.bukkit.entity.Firework spawnedEntity;
	public Firework(int power, Location location) {
		this.power = power;
		this.location = location;
		this.effects = new ArrayList<MFireEffect>();

	}

	public int getPower() {
		return power;
	}

	public Location getLocation() {
		return location;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<MFireEffect> getEffects() {
		return (ArrayList<MFireEffect>) effects.clone();
	}

	public void addEffect(MFireEffect effect) {
		this.effects.add(effect);
	}

	public void remEffect(MFireEffect effect) {
		if (this.effects.contains(effect)) {
			this.effects.remove(effect);
		}
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public void spawnFirework() {
		org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();
		fwm.setPower(power);
		for (MFireEffect e : effects) {
			fwm.addEffect(e.getEffect());
		}
		fw.setFireworkMeta(fwm);
		if(velocity != null){
			fw.setVelocity(velocity);
		}
		spawnedEntity = fw;
	}

	public org.bukkit.entity.Firework getSpawnedEntity() {
		return spawnedEntity;
	}
	

}
