package de.timc.mcorelib.spawner;

import net.minecraft.server.v1_8_R2.BlockPosition;
import net.minecraft.server.v1_8_R2.NBTTagCompound;
import net.minecraft.server.v1_8_R2.TileEntity;
import net.minecraft.server.v1_8_R2.TileEntityMobSpawner;
import net.minecraft.server.v1_8_R2.World;

import org.bukkit.craftbukkit.v1_8_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.block.Block;

public class MCSpawner {

	private Block block;
	private ItemStack item;
	private short range;
	private int delay;
	private short maxNearbyEntities;
	private int playerRange;

	protected MCSpawner(Block b) {
		this.block = b;
	}
	public void update() {
		World world = ((CraftWorld) block.getLocation().getWorld()).getHandle();
		TileEntity tileEntity = world.getTileEntity(new BlockPosition(block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ()));

		if (tileEntity instanceof TileEntityMobSpawner) {
			TileEntityMobSpawner mobSpawner = (TileEntityMobSpawner) tileEntity;
			NBTTagCompound spawnerTag = new NBTTagCompound();
			mobSpawner.b(spawnerTag);

			spawnerTag.remove("SpawnPotentials");
			spawnerTag.setString("EntityId", "Item");
			NBTTagCompound itemTag = new NBTTagCompound();
			itemTag.setShort("Health", (short) 5);
			itemTag.setShort("Age", (short) 0);
			net.minecraft.server.v1_8_R2.ItemStack itemStack = CraftItemStack.asNMSCopy(item);
			NBTTagCompound itemStackTag = new NBTTagCompound();
			itemStack.save(itemStackTag);
			itemStackTag.setByte("Count", (byte) 1);
			itemTag.set("Item", itemStackTag);
			spawnerTag.set("SpawnData", itemTag);
			spawnerTag.setShort("SpawnCount", (short) itemStack.count);
			spawnerTag.setShort("SpawnRange", range);
			spawnerTag.setShort("Delay", (short) 0);
			spawnerTag.setShort("MinSpawnDelay", (short) delay);
			spawnerTag.setShort("MaxSpawnDelay", (short) delay);
			spawnerTag.setShort("MaxNearbyEntities", (short) maxNearbyEntities);
			spawnerTag.setShort("RequiredPlayerRange", (short) (int) playerRange);
			mobSpawner.a(spawnerTag);
		}
	}
	public ItemStack getItem() {
		return item;
	}
	public void setItem(ItemStack item) {
		this.item = item;
	}
	public short getRange() {
		return range;
	}
	public void setRange(short range) {
		this.range = range;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int ticks) {
		this.delay = ticks;
	}
	public short getMaxNearbyEntities() {
		return maxNearbyEntities;
	}
	public void setMaxNearbyEntities(short maxNearbyEntities) {
		this.maxNearbyEntities = maxNearbyEntities;
	}
	public int getPlayerRange() {
		return playerRange;
	}
	public void setPlayerRange(int playerRange) {
		this.playerRange = playerRange;
	}
	public Block getBlock() {
		return block;
	}
	
	
	
}
