package de.timc.mcorelib.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.timc.mcorelib.core.MCore;
import de.timc.mcorelib.logger.Logger;
import de.timc.mcorelib.plugin.MCoreLibPlugin;
import de.timc.mcorelib.plugin.MCorePlayer;

public class ChatProperty extends ListenerProperty {
	private String chatColor;
	private String separator;
	private boolean colorChat;
	private boolean onlyWorldChat;
	private boolean muted;
	private boolean enabled;

	protected ChatProperty(MCoreLibPlugin plugin) {
		super(plugin);
		this.chatColor = "§7";
		this.separator = ": ";
		this.colorChat = false;
		this.onlyWorldChat = false;
		this.muted = false;
		this.enabled = true;
	}

	public boolean isColorChat() {
		return colorChat;
	}

	public void setColorChat(boolean colorChat) {
		this.colorChat = colorChat;
	}

	public boolean isOnlyWorldChat() {
		return onlyWorldChat;
	}

	public void setOnlyWorldChat(boolean onlyWorldChat) {
		this.onlyWorldChat = onlyWorldChat;
	}

	public boolean isMuted() {
		return muted;
	}

	public void setMuted(boolean muted) {
		this.muted = muted;
	}

	public String getChatColor() {
		return chatColor;
	}

	public void setChatColor(String chatColor) {
		this.chatColor = chatColor;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (enabled) {
			MCorePlayer p = MCore.get().getMCorePlayer(e.getPlayer().getName());
			if (!muted && !p.isMutedInChat()) {
				String chatLine = p.getChatPrefix() + p.getChatColorOfName() + e.getPlayer().getName() + separator + p.getChatColorOfText() + (p.isColorChat() ? e.getMessage().replaceAll("&", "§") : e.getMessage());
				for (Player pp : Bukkit.getOnlinePlayers()) {
					MCorePlayer ppp = MCore.get().getMCorePlayer(pp.getName());
					if (ppp.isReceiveChat()) {
						if (onlyWorldChat) {
							if (pp.getWorld().equals(e.getPlayer().getWorld())) {
								pp.sendMessage(chatLine);
							}
						} else {
							pp.sendMessage(chatLine);
						}

					}
				}

				Logger.get().log("[CHAT] " + e.getPlayer().getName() + ": " + e.getMessage());
			}
			e.setCancelled(true);
		}
		
	}
}
