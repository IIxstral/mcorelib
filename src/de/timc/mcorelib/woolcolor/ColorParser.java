package de.timc.mcorelib.woolcolor;

import java.util.ArrayList;

public class ColorParser {
	private String chatColor;
	private byte blockData;
	public static ArrayList<ColorParser> colors = new ArrayList<ColorParser>();
	
	private ColorParser(String chatColor, byte blockData) {
		this.chatColor = chatColor;
		this.blockData = blockData;
		colors.add(this);
	}

	public String getChatColor() {
		return chatColor;
	}

	public byte getBlockData() {
		return blockData;
	}

	public final static ColorParser BLACK = new ColorParser("§0", (byte) 15);
	public final static ColorParser BLUE = new ColorParser("§1", (byte) 11);
	public final static ColorParser GREEN = new ColorParser("§2", (byte) 13);
	public final static ColorParser CYAN = new ColorParser("§3", (byte) 9);
	public final static ColorParser RED = new ColorParser("§4", (byte) 12);
	public final static ColorParser PURPLE = new ColorParser("§5", (byte) 10);
	public final static ColorParser ORANGE = new ColorParser("§6", (byte) 1);
	public final static ColorParser LIGHT_GRAY = new ColorParser("§7", (byte) 8);
	public final static ColorParser GRAY = new ColorParser("§8", (byte) 7);
	public final static ColorParser LIGHT_BLUE = new ColorParser("§9", (byte) 3);
	public final static ColorParser LIGHT_GREEN = new ColorParser("§a", (byte) 5);
	public final static ColorParser LIGHT_RED = new ColorParser("§c", (byte) 14);
	public final static ColorParser PINK = new ColorParser("§d", (byte) 6);
	public final static ColorParser MAGENTA = new ColorParser("§d", (byte) 2);
	public final static ColorParser YELLOW = new ColorParser("§e", (byte) 4);
	public final static ColorParser WHITE = new ColorParser("§f", (byte) 0);

	
	
}
