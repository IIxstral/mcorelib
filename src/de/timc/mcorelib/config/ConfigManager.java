package de.timc.mcorelib.config;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {

	private static ConfigManager instance;
	private ArrayList<Config> configs;

	public static ConfigManager get() {
		return (instance == null ? (instance = new ConfigManager()) : instance);
	}

	private ConfigManager() {
		this.configs = new ArrayList<Config>();
	}

	/**
	 * Erstellt eine Config, die einem Plugin zugeordnet ist. Jedes Plugin kann
	 * eine Config besitzen. Der Name der Config ist <PluginName>.ini
	 * 
	 * @param plugin
	 *            Die Plugin-Instanz, die eine Config anfordert.
	 * @return Ein individuelles Config-Objekt
	 */
	public Config createConfig(JavaPlugin plugin) {
		Config c = new Config(plugin.getName(), plugin.getDataFolder().getAbsolutePath() + "\\" + plugin.getName() + ".ini");
		configs.add(c);
		return c;
	}

	/**
	 * Erstellt eine Config, die einem Plugin zugeordnet ist. Hier besteht
	 * jedoch die Möglichkeit, mehrere Configs pro Plugin zu erstellen, da man
	 * den Dateinamen selbst bestimmen kann (inklusive Endung!)
	 * 
	 * @param plugin
	 *            Die Plugin-Instanz, die eine Config anfordert.
	 * @param fileName
	 *            Der Dateiname der Config (z.B. 'test.txt')
	 * @return Ein individuelles Config-Objekt
	 */
	public Config createConfig(JavaPlugin plugin, String fileName) {
		Config c = new Config(plugin.getName(), plugin.getDataFolder().getParent() + "\\" + fileName);
		configs.add(c);
		return c;
	}

	/**
	 * Erstellt eine Config, die einem Plugin zugeordnet ist. Hier besteht
	 * jedoch die Möglichkeit, mehrere Configs pro Plugin zu erstellen, da man
	 * den Dateinamen selbst bestimmen kann (inklusive Endung!) und den
	 * Dateipfad ändern kann.
	 * 
	 * @param plugin
	 *            Die Plugin-Instanz, die eine Config anfordert.
	 * @param fileName
	 *            Der Dateiname der Config (z.B. 'test.txt')
	 * @param path
	 *            Der Pfad der Config (z.B. 'C:\Bukkit\plugins\Test\') (Wichtig:
	 *            '\' muss am Ende dran sein!)
	 * @return Ein individuelles Config-Objekt
	 */
	public Config createConfig(JavaPlugin plugin, String path, String fileName) {
		Config c = new Config(plugin.getName(), path + fileName);
		configs.add(c);
		return c;
	}

	/**
	 * Gibt alle Configs in einer ArrayList zurück.
	 * 
	 * @return
	 */
	public ArrayList<Config> getConfigs() {
		return configs;
	}

	/**
	 * Gibt mir eine Config passend zu einem Plugin zurück. Wenn mehrere Configs
	 * für ein Plugin existieren, wird die erste Config zurückgegeben.
	 * 
	 * @param pluginName
	 *            Name des Plugins
	 * @return Config, wenn nicht gefunden --> null
	 */
	public Config getConfig(String pluginName) {
		for (Config c : configs) {
			if (c.getPluginName().equals(pluginName)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Gibt mir eine Config passend zu einem Plugin zurück. Wenn mehrere Configs
	 * für ein Plugin existieren, wird die erste Config zurückgegeben.
	 * 
	 * @param plugin
	 *            Plugin-Instanz
	 * @return Config, wenn nicht gefunden --> null
	 */
	public Config getConfig(JavaPlugin plugin) {
		for (Config c : configs) {
			if (c.getPluginName().equals(plugin.getName())) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Gibt alle Configs passend zu einem Plugin zurück.
	 * 
	 * @param pluginName
	 *            Plugin-Name
	 * @return Config, wenn nicht gefunden --> null
	 */
	public ArrayList<Config> getAllConfigs(String pluginName) {
		ArrayList<Config> cc = new ArrayList<Config>();
		for (Config c : configs) {
			if (c.getPluginName().equals(pluginName)) {
				cc.add(c);
			}
		}
		return cc;
	}

	/**
	 * Gibt alle Configs passend zu einem Plugin zurück.
	 * 
	 * @param plugin
	 *            Plugin-Instanz
	 * @return Config, wenn nicht gefunden --> null
	 */
	public ArrayList<Config> getAllConfigs(JavaPlugin plugin) {
		ArrayList<Config> cc = new ArrayList<Config>();
		for (Config c : configs) {
			if (c.getPluginName().equals(plugin.getName())) {
				cc.add(c);
			}
		}
		return cc;
	}

}
