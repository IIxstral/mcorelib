package de.timc.mcorelib.plugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
import net.minecraft.server.v1_8_R2.PacketPlayOutPlayerListHeaderFooter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import de.timc.mcorelib.logger.Logger;
import de.timc.mcorelib.title.Title;
import de.timc.mcorelib.uuid.UUIDFetcher;

public class MCorePlayer {
	private String name;
	private boolean online;
	private ArrayList<Material> blockBreakWhitelist;
	private boolean blockBreakDisabled;
	private ArrayList<Material> blockPlaceWhitelist;
	private boolean blockPlaceDisabled;
	private ArrayList<String> inventoryNameBlacklist;
	private ArrayList<String> damagePlayerWhitelist;
	private boolean colorChat;
	private String chatColor;
	private String chatPrefix;
	private String chatColorName;
	private boolean mutedInChat;
	private boolean movingAllowed;
	private boolean receiveChat;
	private boolean dropItemAllowed;
	private boolean pickupItemAllowed;
	private boolean gameModeChangeAllowed;
	private boolean colorSignWriteAllowed;
	private boolean damageAllowed;
	private boolean ownDamageAllowed;
	private boolean playerDamageAllowed;

	public MCorePlayer(String name) {
		this.name = name;
		this.online = false;
		this.blockBreakDisabled = false;
		this.blockPlaceDisabled = false;
		this.blockBreakWhitelist = new ArrayList<Material>();
		this.blockPlaceWhitelist = new ArrayList<Material>();
		this.inventoryNameBlacklist = new ArrayList<String>();
		this.colorChat = false;
		this.chatColor = "";
		this.chatColorName = "";
		this.mutedInChat = false;
		this.movingAllowed = true;
		this.receiveChat = true;
		this.dropItemAllowed = true;
		this.pickupItemAllowed = true;
		this.gameModeChangeAllowed = true;
		this.colorSignWriteAllowed = false;
		this.chatPrefix = "§e";
		this.damageAllowed = true;
		this.damagePlayerWhitelist = new ArrayList<String>();
		this.ownDamageAllowed = true;
		this.playerDamageAllowed = true;
	}

	public boolean isBlockBreakDisabled() {
		return blockBreakDisabled;
	}

	public void setBlockBreakDisabled(boolean blockBreakDisabled) {
		this.blockBreakDisabled = blockBreakDisabled;
	}

	public ArrayList<Material> getBlockBreakWhitelist() {
		return blockBreakWhitelist;
	}

	public void addBlockBreakMaterialToWhitelist(Material mat) {
		this.blockBreakWhitelist.add(mat);
	}

	public void remBlockBreakMaterialToWhitelist(Material mat) {
		if (blockBreakWhitelist.contains(mat)) {
			this.blockBreakWhitelist.remove(mat);
		}
	}

	public ArrayList<String> getInventoryNameBlacklist() {
		return inventoryNameBlacklist;
	}

	public void addInventoryNameToBlacklist(String invName) {
		this.inventoryNameBlacklist.add(invName);
	}

	public void remInventoryNameToBlacklist(String invName) {
		if (inventoryNameBlacklist.contains(invName)) {
			this.inventoryNameBlacklist.remove(invName);
		}
	}

	public ArrayList<String> getDamagePlayerWhitelist() {
		return damagePlayerWhitelist;
	}

	public void addPlayerNameToDamageWhitelist(String playerName) {
		this.damagePlayerWhitelist.add(playerName);
	}

	public void remPlayerNameToDamageWhitelist(String playerName) {
		if (damagePlayerWhitelist.contains(playerName)) {
			this.damagePlayerWhitelist.remove(playerName);
		}
	}

	public boolean isBlockPlaceDisabled() {
		return blockPlaceDisabled;
	}

	public void setBlockPlaceDisabled(boolean blockPlaceDisabled) {
		this.blockPlaceDisabled = blockPlaceDisabled;
	}

	public ArrayList<Material> getBlockPlaceWhitelist() {
		return blockPlaceWhitelist;
	}

	public void addBlockPlaceMaterialToWhitelist(Material mat) {
		this.blockPlaceWhitelist.add(mat);
	}

	public void remBlockPlaceMaterialToWhitelist(Material mat) {
		if (blockPlaceWhitelist.contains(mat)) {
			this.blockPlaceWhitelist.remove(mat);
		}
	}

	public String getName() {
		return name;
	}

	public boolean isOnline() {
		return online;
	}

	protected void setOnline(boolean online) {
		this.online = online;
	}

	public String getChatColorOfText() {
		return chatColor;
	}

	public void setChatColorOfText(String chatColor) {
		this.chatColor = chatColor;
	}

	public String getChatColorOfName() {
		return chatColorName;
	}

	public void setChatColorOfName(String chatColor) {
		this.chatColorName = chatColor;
	}

	public boolean isMutedInChat() {
		return mutedInChat;
	}

	public void setMutedInChat(boolean mutedInChat) {
		this.mutedInChat = mutedInChat;
	}

	@SuppressWarnings("deprecation")
	public Player getBukkitPlayer() {
		if (online) {
			return Bukkit.getPlayer(name);
		} else {
			//Logger.get().err("Die Methode 'getBukkitPlayer()' des MCore-Spielers '" + name + "' wurde ausgeführt, obwohl der Spieler nicht online ist.");
			return null;
		}
	}

	public void setMovingAllowed(boolean movingEnabled) {
		this.movingAllowed = movingEnabled;
	}

	public boolean isMovingAllowed() {
		return movingAllowed;
	}

	public boolean isReceiveChat() {
		return receiveChat;
	}

	public void setReceiveChat(boolean receiveChat) {
		this.receiveChat = receiveChat;
	}

	public boolean isDropItemAllowed() {
		return dropItemAllowed;
	}

	public void setDropItemAllowed(boolean dropItem) {
		this.dropItemAllowed = dropItem;
	}

	public boolean isGameModeChangeAllowed() {
		return gameModeChangeAllowed;
	}

	public void setGameModeChangeAllowed(boolean gameModeChangeAllowed) {
		this.gameModeChangeAllowed = gameModeChangeAllowed;
	}

	public boolean isPickupItemAllowed() {
		return pickupItemAllowed;
	}

	public void setPickupItemAllowed(boolean pickupItemAllowed) {
		this.pickupItemAllowed = pickupItemAllowed;
	}

	public boolean isColorSignWriteAllowed() {
		return colorSignWriteAllowed;
	}

	public void setColorSignWriteAllowed(boolean allow) {
		this.colorSignWriteAllowed = allow;
	}

	public String getChatPrefix() {
		return chatPrefix;
	}

	public void setChatPrefix(String chatPrefix) {
		this.chatPrefix = chatPrefix;
	}

	public UUID getRealUUID() {
		try {
			return UUIDFetcher.getUUIDOf(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isDamageAllowed() {
		return damageAllowed;
	}

	public void setDamageAllowed(boolean damageAllowed) {
		this.damageAllowed = damageAllowed;
	}

	public boolean isOwnDamageAllowed() {
		return ownDamageAllowed;
	}

	public void setOwnDamageAllowed(boolean ownDamageAllowed) {
		this.ownDamageAllowed = ownDamageAllowed;
	}

	public boolean isPlayerDamageAllowed() {
		return playerDamageAllowed;
	}

	public void setPlayerDamageAllowed(boolean playerDamageAllowed) {
		this.playerDamageAllowed = playerDamageAllowed;
	}

	@SuppressWarnings("deprecation")
	public Scoreboard showScoreboard(String displayName, DisplaySlot slot, String... lines) {
		Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = s.registerNewObjective("" + System.currentTimeMillis(), "dummy");
		o.setDisplayName(displayName);
		o.setDisplaySlot(slot);
		if (lines != null) {
			int i = lines.length - 1;
			for (String l : lines) {
				o.getScore(l).setScore(i);
				i--;
			}
		}
		Player p = Bukkit.getPlayer(name);
		if (p != null && p.isOnline()) {
			p.setScoreboard(s);
		}
		return s;
	}

	public boolean isColorChat() {
		return colorChat;
	}

	public void setColorChat(boolean colorChat) {
		this.colorChat = colorChat;
	}

	@SuppressWarnings("deprecation")
	public void sendTitle(Title t) {
		String[] tmp = t.toJSON(name);
		String val = Bukkit.getPlayer(name).getWorld().getGameRuleValue("sendCommandFeedback");
		Bukkit.getPlayer(name).getWorld().setGameRuleValue("sendCommandFeedback", "false");
		for (String s : tmp) {
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), s);
		}
		Bukkit.getPlayer(name).getWorld().setGameRuleValue("sendCommandFeedback", val);
	}

	public void sendTitleHotbar(String message) {
		IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
		((CraftPlayer) getBukkitPlayer()).getHandle().playerConnection.sendPacket(ppoc);
	}

	public void sendTabText(String header, String footer) {
		IChatBaseComponent h = ChatSerializer.a("{text:\"" + header + "\"}");
		IChatBaseComponent f = ChatSerializer.a("{text:\"" + footer + "\"}");

		PacketPlayOutPlayerListHeaderFooter headerFooter = new PacketPlayOutPlayerListHeaderFooter(h);
		try {
			Field bField = headerFooter.getClass().getDeclaredField("b");
			bField.setAccessible(true);
			bField.set(headerFooter, f);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		if(getBukkitPlayer() != null){
			((CraftPlayer) getBukkitPlayer()).getHandle().playerConnection.sendPacket(headerFooter);
		}
		
	}
}
