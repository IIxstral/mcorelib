package de.timc.mcorelib.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.timc.mcorelib.armorstands.ArmorStandProperty;
import de.timc.mcorelib.countdown.Countdown;
import de.timc.mcorelib.countdown.CountdownProperty;
import de.timc.mcorelib.dragonbar.Dragon;
import de.timc.mcorelib.effectlib.EffectManager;
import de.timc.mcorelib.effectlib.entity.EntityManager;
import de.timc.mcorelib.effectlib.listener.ItemListener;
import de.timc.mcorelib.jsonchat.JSONChatMessage;
import de.timc.mcorelib.plugin.MCoreLibPlugin;
import de.timc.mcorelib.plugin.MCorePlayer;
import de.timc.mcorelib.servertool.ServerTool;
import de.timc.mcorelib.sql.MCoreSQL;
import de.timc.mcorelib.update.MCoreUpdate;
import de.timc.mcorelib.update.MCoreUpdateManager;
import de.timc.mcorelib.uuid.UUIDFetcher;
import de.timc.mcorelib.woolcolor.ColorParser;

public class MCore {
	private static MCore instance;
	private MCoreLibPlugin plugin;
	private EntityManager entityManager;
	private EffectManager manager;
	private ServerTool serverTool;
	public static String version = "0.2.6";
	public static String header = "[MCore] ";
	public ArrayList<MCorePlayer> players;
	private boolean loginAllowed;
	private boolean blockPhysicsAllowed;
	private String joinMessage;
	private String leaveMessage;
	private boolean updateFound;
	
	public static MCore get() {
		return (instance == null ? (instance = new MCore()) : instance);
	}

	protected MCore() {
		System.out.println("Test2");
		this.players = new ArrayList<MCorePlayer>();
		this.loginAllowed = true;
		this.blockPhysicsAllowed = true;
		this.joinMessage = "§e%u betritt das Spiel.";
		this.leaveMessage = "§e%u verlies das Spiel.";

		new Thread(new Runnable() {

			@Override
			public void run() {
				MCoreUpdate upd = MCoreUpdateManager.checkUpdate();
				/*
				 * 88b           d88    ,ad8888ba,                                        88           88  88           
888b         d888   d8"'    `"8b                                       88           ""  88           
88`8b       d8'88  d8'                                                 88               88           
88 `8b     d8' 88  88              ,adPPYba,   8b,dPPYba,   ,adPPYba,  88           88  88,dPPYba,   
88  `8b   d8'  88  88             a8"     "8a  88P'   "Y8  a8P_____88  88           88  88P'    "8a  
88   `8b d8'   88  Y8,            8b       d8  88          8PP"""""""  88           88  88       d8  
88    `888'    88   Y8a.    .a8P  "8a,   ,a8"  88          "8b,   ,aa  88           88  88b,   ,a8"  
88     `8'     88    `"Y8888Y"'    `"YbbdP"'   88           `"Ybbd8"'  88888888888  88  8Y"Ybbd8"'   
                                                                                                     
                                                                                                     
				 */
				System.out.println("88b           d88    ,ad8888ba,                                        88           88  88");
				System.out.println("888b         d888   d8\"'    `\"8b                                       88           \"\"  88           ");
				System.out.println("88`8b       d8'88  d8'                                                 88               88           ");
				System.out.println("88 `8b     d8' 88  88              ,adPPYba,   8b,dPPYba,   ,adPPYba,  88           88  88,dPPYba,   ");
				System.out.println("88  `8b   d8'  88  88             a8\"     \"8a  88P'   \"Y8  a8P_____88  88           88  88P'    \"8a  ");
				System.out.println("88   `8b d8'   88  Y8,            8b       d8  88          8PP\"\"\"\"\"\"\"  88           88  88       d8  ");
				System.out.println("88    `888'    88   Y8a.    .a8P  \"8a,   ,a8\"  88          \"8b,   ,aa  88           88  88b,   ,a8\"  ");
				System.out.println("88     `8'     88    `\"Y8888Y\"'    `\"YbbdP\"'   88           `\"Ybbd8\"'  88888888888  88  8Y\"Ybbd8\"'   ");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				
				if (upd != null) {
					updateFound = true;
					System.out.println("[MCoreLib] A new version of MCoreLib is now available! Check it out soon!");
					System.out.println("[MCoreLib] Your version: '" + version + "', new version: '" + upd.getVersion() + " >" + upd.getVersionState() + "<'!");
					System.out.println("[MCoreLib] Download-Link: " + upd.getPath());
					System.out.println("[MCoreLib] Things changed in new version (Changelog):");
					for (String tmp : upd.getChangelog()) {
						System.out.println("[MCoreLib] [Changelog] " + tmp);
					}
					System.out.println("[MCoreLib] You don't need to update, but it is highly recommended.");
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.isOp()) {
							p.sendMessage(header + "§eFor the MCore library is a new version available. More information in your console.");
						}
					}
				} else {
					System.out.println("[MCoreLib] MCoreLib version " + version + " activated and running!");
				}
			}

		}).start();
	}

	public Countdown createCountdown(int ticks) {
		Countdown c = new Countdown(ticks);
		return c;
	}

	public Countdown createCountdown(int ticks, int ticktime) {
		Countdown c = new Countdown(ticks, ticktime);
		return c;
	}

	public Countdown createCountdown(int ticks, int ticktime, CountdownProperty property) {
		Countdown c = new Countdown(ticks, ticktime, property);
		return c;
	}

	// public void spawnFirework(World w, Location loc) {
	// //Firework f = (Firework) w.spawnEntity(loc,
	// org.bukkit.entity.EntityType.FIREWORK);
	// }

	public EntityManager getEffectEntityManager() {
		return entityManager;
	}

	public EffectManager getEffectManager() {
		return manager;
	}

	public void removeStatusBar(Player player) {
		Dragon.removeStatusBar(player);
	}

	public void setStatusBar(Player player, String text, float percent) {
		Dragon.setStatusBar(player, text, percent);
	}

	public void removeAllStatusBars() {
		Dragon.removeAllStatusBars();
	}

	public void setAllStatusBars(String text, float percent) {
		Dragon.setAllStatusBars(text, percent);
	}

	public MCoreLibPlugin getPlugin() {
		return plugin;
	}

	public ServerTool getServerTool() {
		return serverTool;
	}

	public void setPluginInstance(MCoreLibPlugin plugin) {
		this.plugin = plugin;
		this.serverTool = new ServerTool(plugin);
		entityManager = new EntityManager(plugin);
		EffectManager.initialize();
		manager = new EffectManager(plugin);
		Bukkit.getPluginManager().registerEvents(new ItemListener(), plugin);
	}

	public UUID getRealUUID(String playerName) throws Exception {
		return UUIDFetcher.getUUIDOf(playerName);
	}

	public MCoreSQL createSQL(String dbHost, String port, String database, String dbUser, String dbPasswort) {
		return new MCoreSQL(dbHost, port, database, dbUser, dbPasswort);
	}


	public ItemStack createItem(Material mat, int anzahl, String name, List<String> lore, String... enchants) {
		ItemStack i = new ItemStack(mat, anzahl);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(name);
		m.setLore(lore);
		i.setItemMeta(m);
		String[] c = enchants.clone();
		Enchantment e = null;
		boolean has = false;
		try {
			for (String s : c) {
				if (!has) {
					e = Enchantment.getByName(s);
					has = true;
				} else {
					i.addUnsafeEnchantment(e, Integer.valueOf(s));
					has = false;
				}
			}
		} catch (NumberFormatException ex) {
			System.err.println("Fehler bei der Bestimmung des Items. Falsche Zahl eingegeben.");
		}

		return i;

	}
	public ItemStack createItem(Material mat, int anzahl, String name) {
		return createItem(mat, anzahl, name, null);
	}

	public ColorParser parseWoolColorToChatColor(byte woolData) {
		@SuppressWarnings("unused")
		ColorParser unuse = ColorParser.BLACK;

		for (ColorParser c : ColorParser.colors) {
			if (c.getBlockData() == woolData) {
				return c;
			}
		}
		return null;
	}

	public ColorParser parseChatColorToWoolColor(String chatColorChar) {
		// unuse ist für das Aufrufen des private constructors vorhanden.
		@SuppressWarnings("unused")
		ColorParser unuse = ColorParser.BLACK;

		for (ColorParser c : ColorParser.colors) {
			if (c.getChatColor().equalsIgnoreCase("§" + chatColorChar)) {
				return c;
			}
		}
		return null;
	}

	public void sendFormattedChatMessageToPlayer(JSONChatMessage m, Player p) {
		m.sendToPlayer(p);
	}

	public MCorePlayer getMCorePlayer(String name) {
		for (MCorePlayer p : players) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		MCorePlayer p = new MCorePlayer(name);
		players.add(p);
		return p;
	}

	public boolean isLoginAllowed() {
		return loginAllowed;
	}

	public void setLoginAllowed(boolean loginAllowed) {
		this.loginAllowed = loginAllowed;
	}

	public boolean isBlockPhysicsAllowed() {
		return blockPhysicsAllowed;
	}

	public void setBlockPhysicsAllowed(boolean blockPhysicsAllowed) {
		this.blockPhysicsAllowed = blockPhysicsAllowed;
	}

	public String getJoinMessage() {
		return joinMessage;
	}

	public void setJoinMessage(String joinMessage) {
		this.joinMessage = joinMessage;
	}

	public String getLeaveMessage() {
		return leaveMessage;
	}

	public void setLeaveMessage(String leaveMessage) {
		this.leaveMessage = leaveMessage;
	}

	public void spawnArmorStand(Location location, ArmorStandProperty property) {
		String command = "summon ArmorStand " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + " " + property.toJSON();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
	}
	public String returnspawnArmorStand(Location location, ArmorStandProperty property) {
		return "summon ArmorStand " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + " " + property.toJSON();
	}

	public boolean isUpdateFound() {
		return updateFound;
	}
	
	
}
