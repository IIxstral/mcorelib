package de.timc.mcorelib.countdown;

public class CountdownMessageHandler {
	private int[] times;
	private String message;
	public CountdownMessageHandler(String message, int... times){
		this.times = times;
		this.message = message;
	}
	public int[] getTimes() {
		return times;
	}
	public String getMessage() {
		return message;
	}
	
}
