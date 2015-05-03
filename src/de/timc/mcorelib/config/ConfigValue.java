package de.timc.mcorelib.config;

import java.util.ArrayList;

public class ConfigValue {
	private String key;
	private String value;
	private ArrayList<String> commentLines;
	protected ConfigValue(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	protected ConfigValue(String key, String value, ArrayList<String> commentLines) {
		this.key = key;
		this.value = value;
		this.commentLines = commentLines;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public ArrayList<String> getCommentLines() {
		return commentLines;
	}

	protected void setKey(String key) {
		this.key = key;
	}

	protected void setValue(String value) {
		this.value = value;
	}

	protected void setCommentLines(ArrayList<String> commentLines) {
		this.commentLines = commentLines;
	}
	
	

}
