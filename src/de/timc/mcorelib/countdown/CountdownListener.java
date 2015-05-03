package de.timc.mcorelib.countdown;

public interface CountdownListener {
	public void onTick(CountdownTickEvent event);
	public void onFinish(CountdownTickEvent event);
}
