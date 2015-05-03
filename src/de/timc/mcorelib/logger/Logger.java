package de.timc.mcorelib.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
	private static Logger instance;
	private File file;
	private SimpleDateFormat format;
	private boolean enabled;
	
	public static Logger get() {
		return (instance == null ? (instance = new Logger()) : instance);
	}

	private Logger() {
		setLogPath("logs\\log.log");
		this.enabled = true;
		this.format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public void setLogPath(String path) {
		this.file = new File(path);
		if (!this.file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void log(String line) {
		String l = "[XINFO] [" + format.format(Calendar.getInstance().getTime()) + "] " + line;
		if (enabled) {
			sendToLog(l);
		}
		System.out.println(l);
	}

	public void err(String line) {
		String l = "[ERROR] [" + format.format(Calendar.getInstance().getTime()) + "] " + line;
		if (enabled) {
			sendToLog(l);
		}
		System.out.println(l);
	}

	private void sendToLog(String line) {
		BufferedWriter w;
		try {
			w = new BufferedWriter(new FileWriter(file, true));
			w.write(line + "\r\n");
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public SimpleDateFormat getDateFormat() {
		return format;
	}

	public void setDateFormat(SimpleDateFormat format) {
		this.format = format;
	}

}
