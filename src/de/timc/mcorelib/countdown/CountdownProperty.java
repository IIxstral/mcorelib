package de.timc.mcorelib.countdown;


public class CountdownProperty {
	private CountdownMessageHandler[] handlers;
	
	public CountdownProperty(){
		
	}
	
	public CountdownProperty setMessageHandlers(CountdownMessageHandler... handlers){
		this.handlers = handlers;
		return this;
	}

	public CountdownMessageHandler[] getHandlers() {
		return handlers;
	}
	
	
}
