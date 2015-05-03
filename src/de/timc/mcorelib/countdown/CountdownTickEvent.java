package de.timc.mcorelib.countdown;

public class CountdownTickEvent {
	private Countdown countdown;
	private int currentTime;
	private int leftTime;
	
	protected CountdownTickEvent(Countdown countdown, int currentTime, int leftTime){
		this.countdown = countdown;
		this.currentTime = currentTime;
		this.leftTime = leftTime;
	}

	public Countdown getCountdown() {
		return countdown;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public int getLeftTime() {
		return leftTime;
	}
	
}
