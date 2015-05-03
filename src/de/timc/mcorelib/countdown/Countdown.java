package de.timc.mcorelib.countdown;

import java.util.ArrayList;

public class Countdown {
	private int ticks;
	private int tickTime;
	private ArrayList<CountdownListener> events;
	private CountdownTicker ticker;
	private CountdownProperty property;
	private boolean finished;
	
	public Countdown(int ticks) {
		this.ticks = ticks;
		this.tickTime = 1000;
		this.events = new ArrayList<CountdownListener>();
		this.ticker = new CountdownTicker(this);
	}

	public Countdown(int ticks, int tickTime, CountdownProperty property) {
		this(ticks, tickTime);
		this.property = property;
	}

	public Countdown(int ticks, int tickTime) {
		this(ticks);
		this.tickTime = tickTime;
	}

	public void start() {
		new Thread(ticker).start();
	}

	public void stop() {
		finished = true;
	}

	public void addListener(CountdownListener event) {
		this.events.add(event);
	}

	public int getTicks() {
		return ticks;
	}

	public int getTickTime() {
		return tickTime;
	}

	public CountdownTicker getTicker() {
		return ticker;
	}

	public CountdownProperty getProperty() {
		return property;
	}

	public boolean isFinished() {
		return finished;
	}

	protected void setFinished(boolean finished) {
		this.finished = finished;
	}

	protected void pushListenerTick(int count) {
		for (CountdownListener l : events) {
			l.onTick(new CountdownTickEvent(this, ticks - count, count));
		}
	}

	protected void pushListenerFinish(int count) {
		for (CountdownListener l : events) {
			l.onFinish(new CountdownTickEvent(this, ticks - count, count));
		}
	}

}
