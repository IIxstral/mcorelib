package de.timc.mcorelib.servertool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * Einfache API zum Verwalten des Bungee-Servers.
 * 
 * @author TiSan
 * 
 */
public class ServerTool implements PluginMessageListener {

	private JavaPlugin plugin;
	private ArrayList<String[]> queue;
	private String pluginName;

	/**
	 * Erstellt eine Instanz des ServerTools
	 * 
	 * @param plugin
	 *            JavaPlugin
	 */
	public ServerTool(JavaPlugin plugin) {
		this.plugin = plugin;
		this.queue = new ArrayList<String[]>();
		Bukkit.getMessenger().registerOutgoingPluginChannel(this.plugin, "BungeeCord");
		Bukkit.getMessenger().registerIncomingPluginChannel(this.plugin, "BungeeCord", this);
	}

	/**
	 * Sendet den angegebenen Spieler von diesem Server auf einen anderen.
	 * 
	 * @param player
	 *            Der zu verschiebende Spieler.
	 * @param serverName
	 *            ServerName
	 */
	public void sendPlayerToServer(Player player, String serverName) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(serverName);
		} catch (IOException e) {
			System.err.println("No connection to server.");
		}
		player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
	}

	/**
	 * Sendet einen Spieler von einem anderen Server auf einen anderen
	 * Unterserver.
	 * 
	 * @param commander
	 *            Commando-Ausführer
	 * @param player
	 *            Der zu verschiebende Spieler
	 * @param serverName
	 *            ServerName
	 */
	public void sendOtherPlayerToServer(Player commander, String player, String serverName) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);

		try {
			out.writeUTF("ConnectOther");
			out.writeUTF(player);
			out.writeUTF(serverName);
		} catch (IOException e) {
			System.err.println("No connection to server.");
		}
		commander.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
	}

	/**
	 * Schicke Befehl zum Empfangen der Spieleranzahl
	 * 
	 * @param commander
	 *            Commando-Ausführer
	 * @param serverName
	 *            ServerName
	 */
	public void requestPlayerCount(Player commander, String serverName) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("PlayerCount");
			out.writeUTF(serverName);
		} catch (IOException e) {
			System.err.println("No connection to server.");
		}
		commander.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
		// getPlayerCount(serverName);
	}

	/**
	 * Gebe angeforderte Spieleranzahl des Servers zurück
	 * 
	 * @param serverName
	 *            ServerName
	 * @return Spieleranzahl
	 */
	public int getPlayerCount(String serverName) {
		try {
			for (String[] f : queue) {
				if (f != null && f.length > 1) {
					if (f[0].equals("PlayerCount") && f[1].equals(serverName)) {
						queue.remove(f);
						return Integer.valueOf(f[2]);
					}
				} else {
					return 0;
				}

			}
		} catch (ConcurrentModificationException ex) {

		}

		return -1;
	}

	/**
	 * Sende Befehl zum Empfangen der Spielerliste eines Servers
	 * 
	 * @param commander
	 *            Commando-Ausführer
	 * @param serverName
	 *            ServerName
	 */
	public void requestPlayerList(Player commander, String serverName) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("PlayerList");
			out.writeUTF(serverName);
		} catch (IOException e) {
			System.err.println("No connection to server.");
		}
		commander.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
		// getPlayerList(serverName);
	}

	/**
	 * Gebe Spielerliste für den bestimmten Server zurück
	 * 
	 * @param serverName
	 *            ServerName
	 * @return Spielerliste
	 */
	public String[] getPlayerList(String serverName) {
		try {
			String[] tmp;
			for (String[] f : queue) {
				if (f[0].equals("PlayerList") && f[1].equals(serverName)) {
					tmp = new String[f.length - 2];
					for (int i = 0; i < f.length - 2; i++) {
						tmp[i] = f[i + 2];
					}
					queue.remove(f);
					return tmp;
				}
			}
		} catch (ConcurrentModificationException ex) {

		}
		return null;
	}

	/**
	 * Sende eine Nachricht an eine Person im Netzwerk.
	 * 
	 * @param commander
	 *            Commando-Ausführer
	 * @param player
	 *            Der zu erhaltende Spieler
	 * @param message
	 *            Nachricht
	 */
	public void sendMessageTo(Player commander, String player, String message) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Message");
			out.writeUTF(player);
			out.writeUTF(message);
		} catch (IOException e) {
			System.err.println("No connection to server.");
		}
		commander.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
	}

	/**
	 * Not for you, bro!
	 */
	@Override
	public void onPluginMessageReceived(final String channel, final Player player, final byte[] message) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (!channel.equals("BungeeCord")) {
					return;
				}
				try {
					DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
					String subchannel = in.readUTF();
					if (subchannel.equals("PlayerCount")) {
						String server = in.readUTF();
						int playercount = in.readInt();
						queue.add(new String[] { "PlayerCount", server, playercount + "" });
					} else if (subchannel.equals("PlayerList")) {
						String server = in.readUTF();
						String[] playerList = in.readUTF().split(", ");
						String[] tmp = new String[playerList.length + 2];
						tmp[0] = "PlayerList";
						tmp[1] = server;
						for (int i = 0; i < playerList.length; i++) {
							tmp[i + 2] = playerList[i];
						}
						queue.add(tmp);
					} else if (subchannel.equals("GetServers")) {
						queue.add(new String[] { "GetServers", in.readUTF() });
					}
				} catch (IOException e) {
				}
			}

		}).start();
	}

	public void requestServerList(Player commander) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("GetServers");
		} catch (IOException e) {
			System.err.println("No connection to server.");
		}
		commander.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
		// getPlayerList(serverName);
	}

	public String[] getServerList() {
		for (String[] t : queue) {
			if (t[0].equalsIgnoreCase("GetServers")) {
				return t[1].split(", ");
			}
		}
		return new String[1];
	}

	public String getPluginName() {
		return pluginName;
	}
}
