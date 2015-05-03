package de.timc.mcorelib.countdown;

import org.bukkit.Bukkit;

import de.timc.mcorelib.core.MCore;

public class CountdownTicker implements Runnable{
	
	private Countdown countdown;

	protected CountdownTicker(Countdown c){
		this.countdown = c;
	}

	@Override
	public void run() {
		int count = countdown.getTicks();
		while(MCore.get().getPlugin().isEnabled() && !countdown.isFinished()){
			if(countdown.getProperty() != null){
				for(CountdownMessageHandler h : countdown.getProperty().getHandlers()){
					for(int t : h.getTimes()){
						if(t == count){
							Bukkit.broadcastMessage(h.getMessage());
						}
					}
				}
			}
			this.countdown.pushListenerTick(count);
			if(count == 0){
				this.countdown.pushListenerFinish(count);
				this.countdown.setFinished(true);
				return;
			}
			count--;
			
			try {
				Thread.sleep(countdown.getTickTime());
			} catch (InterruptedException e) {
			}
		}
	}
}