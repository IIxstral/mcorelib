package de.timc.mcorelib.spawner;

import org.bukkit.block.Block;

public class SpawnerManager {

	private static SpawnerManager instance;

	public static SpawnerManager get(){
		return (instance == null ? (instance = new SpawnerManager()) : instance);
	}
	
	public MCSpawner createSpawner(Block b){
		return new MCSpawner(b);
	}
	
	
}
