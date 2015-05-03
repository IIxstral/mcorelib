package de.timc.mcorelib.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class Config {
	private File file;
	private String pluginName;
	private ArrayList<ConfigValue> values;

	protected Config(String pluginName, String fileName) {
		System.out.println("Creating config instance by " + pluginName + ", fileName: " + fileName);
		this.pluginName = pluginName;
		this.values = new ArrayList<ConfigValue>();
		try {
			this.file = new File(fileName);
			if (!this.file.exists()) {
				new File(file.getParent() + "\\").mkdirs();
				this.file.createNewFile();
				saveConfig();
				System.out.println("File not found, creating a new config file.");
			}
			reloadConfig();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lädt die Einträge aus der Config erneut. Wenn die Config nicht existieren
	 * sollte, wird eine Exception zurückgegeben.
	 * 
	 * @throws IOException
	 */
	public void reloadConfig() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		ArrayList<String> lines = new ArrayList<String>();
		String l = "";
		while ((l = reader.readLine()) != null) {
			if (!l.startsWith("#") && !l.equals("")) {
				lines.add(l);
			}
		}
		reader.close();
		for (String ll : lines) {
			if (ll.contains("=")) {
				String prefix = ll.split("=")[0];
				String args;
				if (ll.split("=").length > 1) {
					args = ll.split("=")[1];
				} else {
					args = "";
				}
				if (prefix.length() > 0) {
					values.add(new ConfigValue(prefix, args));
				}
			}

		}
	}

	/**
	 * Die Config wird in die Datei geschrieben.
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void saveConfig() throws IOException, URISyntaxException {
		BufferedWriter w = new BufferedWriter(new FileWriter(file));
		w.write("# Settings for " + pluginName + "." + "\r\n");
		w.write("# Created by MCoreLib, an easy management bukkit system by TiSan and MCfrek99 (http://tisan.de)." + "\r\n");
		for (ConfigValue v : values) {
			if (v.getCommentLines() != null) {
				for (String l : v.getCommentLines()) {
					w.write("# " + l + "\r\n");
				}
				w.write("\r\n");
			}
			w.write(v.getKey() + "=" + v.getValue() + "\r\n");
			w.write("\r\n");
			w.write("\r\n");
		}
		w.flush();
		w.close();
	}

	/**
	 * Hier wird ein Wert aus der Config abgefragt. Der Key ist der vordere Teil
	 * eines Config-Eintrags, der Value der hintere Teil. Wenn der Eintrag nicht
	 * gefunden wurde, wird eine Exception zurückgegeben.
	 * 
	 * @param key
	 * @return Value of the config.
	 * @throws ConfigValueNotFoundException
	 */
	public String getValueString(String key) throws ConfigValueNotFoundException {
		for (ConfigValue v : values) {
			if (key.equals(v.getKey())) {
				return v.getValue();
			}
		}
		throw new ConfigValueNotFoundException();
	}

	/**
	 * Hier wird ein Wert aus der Config abgefragt. Der Key ist der vordere Teil
	 * eines Config-Eintrags, der Value der hintere Teil. Wenn der Eintrag nicht
	 * gefunden wurde, wird eine Exception zurückgegeben.
	 * 
	 * @param key
	 * @return Value of the config.
	 * @throws ConfigValueNotFoundException
	 */
	public int getValueInt(String key) throws ConfigValueNotFoundException {
		for (ConfigValue v : values) {
			if (key.equals(v.getKey())) {
				return Integer.valueOf(v.getValue());
			}
		}
		throw new ConfigValueNotFoundException();
	}

	/**
	 * Hier wird ein Wert aus der Config abgefragt. Der Key ist der vordere Teil
	 * eines Config-Eintrags, der Value der hintere Teil. Wenn der Eintrag nicht
	 * gefunden wurde, wird eine Exception zurückgegeben.
	 * 
	 * @param key
	 * @return Value of the config.
	 * @throws ConfigValueNotFoundException
	 */
	public double getValueDouble(String key) throws ConfigValueNotFoundException {
		for (ConfigValue v : values) {
			if (key.equals(v.getKey())) {
				return Double.valueOf(v.getValue());
			}
		}
		throw new ConfigValueNotFoundException();
	}

	/**
	 * Hier wird ein Wert aus der Config abgefragt. Der Key ist der vordere Teil
	 * eines Config-Eintrags, der Value der hintere Teil. Wenn der Eintrag nicht
	 * gefunden wurde, wird eine Exception zurückgegeben.
	 * 
	 * @param key
	 * @return Value of the config.
	 * @throws ConfigValueNotFoundException
	 */
	public boolean getValueBoolean(String key) throws ConfigValueNotFoundException {
		for (ConfigValue v : values) {
			if (key.equals(v.getKey())) {
				return Boolean.valueOf(v.getValue());
			}
		}
		throw new ConfigValueNotFoundException();
	}

	/**
	 * Hier wird ein Wert aus der Config abgefragt. Der Key ist der vordere Teil
	 * eines Config-Eintrags, der Value der hintere Teil. Wenn der Eintrag nicht
	 * gefunden wurde, wird eine Exception zurückgegeben.
	 * 
	 * @param key
	 * @return Value of the config.
	 * @throws ConfigValueNotFoundException
	 */
	public float getValueFloat(String key) throws ConfigValueNotFoundException {
		for (ConfigValue v : values) {
			if (key.equals(v.getKey())) {
				return Float.valueOf(v.getValue());
			}
		}
		throw new ConfigValueNotFoundException();
	}

	private ConfigValue getConfigValue(String key) {
		for (ConfigValue v : values) {
			if (key.equals(v.getKey())) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Speichere einen Wert in die Config. Zum endgültigen Speichern bitte
	 * saveConfig() benutzen.
	 * 
	 * @param key
	 *            Schlüssel
	 * @param value
	 *            Wert
	 */
	public void updateValue(String key, String value) {
		if (containsValue(key)) {
			getConfigValue(key).setKey(key);
			getConfigValue(key).setValue(value);
		} else {
			values.add(new ConfigValue(key, value));
		}

	}

	/**
	 * Speichere einen Wert in die Config und bietet die Möglichkeit zur
	 * Kommentarsetzung eines Eintrags. Somit kann der Benutzer wissen, was
	 * welcher Eintrag bewirkt. Zum endgültigen Speichern bitte saveConfig()
	 * benutzen.
	 * 
	 * @param key
	 *            Schlüssel
	 * @param value
	 *            Wert
	 * @param commentLines
	 *            Die Lines des Kommentars zum Config-Eintrag.
	 */
	public void updateValue(String key, String value, String... commentLines) {
		ArrayList<String> commLine = new ArrayList<String>(Arrays.asList(commentLines));
		if (containsValue(key)) {
			getConfigValue(key).setKey(key);
			getConfigValue(key).setValue(value);
			getConfigValue(key).setCommentLines(commLine);
		} else {
			values.add(new ConfigValue(key, value, commLine));
		}
	}

	/**
	 * Enthält die Config den Key?
	 * 
	 * @param key
	 *            Der zu überprüfende Key
	 * @return Enthalten oder nicht
	 */
	public boolean containsValue(String key) {
		for (ConfigValue v : values) {
			if (v.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Löscht einen Eintrag aus der Config mit dem Key.
	 * 
	 * @param key
	 *            Der zu überprüfende Key.
	 * @return Wurde er gelöscht oder nicht?
	 */
	public boolean removeValue(String key) {
		for (ConfigValue v : values) {
			if (v.getKey().equals(key)) {
				values.remove(v);
				return true;
			}
		}
		return false;
	}

	/**
	 * Löscht die komplette Config und schreibt es in die Datei.
	 */
	public void clearConfig() {
		values.clear();
		try {
			saveConfig();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public String getPluginName() {
		return pluginName;
	}

}
