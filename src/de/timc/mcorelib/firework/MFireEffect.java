package de.timc.mcorelib.firework;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;

public class MFireEffect {
	private boolean flicker;
	private ArrayList<Color> flickerColor;
	private ArrayList<Color> flickerFadeColor;
	private Type flickerType;
	
	private boolean trail;
	private ArrayList<Color> trailColor;
	private ArrayList<Color> trailFadeColor;
	private Type trailType;
	
	public MFireEffect(){
		this.flicker = false;
		this.flickerColor = new ArrayList<Color>();
		this.flickerFadeColor = new ArrayList<Color>();
		this.trailColor = new ArrayList<Color>();
		this.trailFadeColor = new ArrayList<Color>();
	}
	public boolean isFlicker() {
		return flicker;
	}
	public void setFlicker(boolean flicker) {
		this.flicker = flicker;
	}
	public ArrayList<Color> getFlickerColor() {
		return flickerColor;
	}
	public void addFlickerColor(Color color) {
		this.flickerColor.add(color);
	}
	
	public ArrayList<Color> getFlickerFadeColor() {
		return flickerFadeColor;
	}
	public void addFlickerFadeColor(Color color) {
		this.flickerFadeColor.add(color);
	}
	public Type getFlickerType() {
		return flickerType;
	}
	public void setFlickerType(Type flickerType) {
		this.flickerType = flickerType;
	}
	public boolean isTrail() {
		return trail;
	}
	public void setTrail(boolean trail) {
		this.trail = trail;
	}
	public ArrayList<Color> getTrailColor() {
		return trailColor;
	}
	public void addTrailColor(Color color) {
		this.trailColor.add(color);
	}
	public ArrayList<Color> getTrailFadeColor() {
		return trailFadeColor;
	}
	public void addFadeTrailColor(Color color) {
		this.trailFadeColor.add(color);
	}
	public Type getTrailType() {
		return trailType;
	}
	public void setTrailType(Type trailType) {
		this.trailType = trailType;
	}
	
	public FireworkEffect getEffect(){
		Builder builder = FireworkEffect.builder();
        if(flicker){
       	 builder = builder.flicker(true);
       	 if(flickerColor.size() > 0){
       		 builder = builder.withColor(flickerColor.toArray(new Color[flickerColor.size()]));
       	 }
       	 if(flickerFadeColor.size() > 0){
       		builder = builder.withFade(flickerFadeColor.toArray(new Color[flickerFadeColor.size()]));
       	 }
       	 if(flickerType != null){
       		 builder = builder.with(flickerType);
       	 }
        }
        if(trail){
       	 builder = builder.trail(true);
       	 if(trailColor.size() > 0){
       		 builder = builder.withColor(trailColor.toArray(new Color[trailColor.size()]));
       	 }
       	 if(trailFadeColor.size() > 0){
       		builder = builder.withFade(trailFadeColor.toArray(new Color[trailFadeColor.size()]));
       	 }
       	 if(trailType != null){
       		 builder = builder.with(trailType);
       	 }
        }
        FireworkEffect e = builder.build();
        return e;
	}
	
}	
