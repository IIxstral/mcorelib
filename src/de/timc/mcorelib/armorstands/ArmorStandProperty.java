package de.timc.mcorelib.armorstands;

import org.bukkit.Material;

public class ArmorStandProperty {
	private boolean visible;
	private boolean gravity;
	private boolean basePlate;
	private boolean armsDisplayed;
	private boolean small;
	private float rotation;
	private Material itemHand;
	private Material itemHelmet;
	private Material itemChestplate;
	private Material itemLeggings;
	private Material itemBoots;
	private Position posHead;
	private Position posBody;
	private Position posLeftLeg;
	private Position posRightLeg;
	private Position posLeftArm;
	private Position posRightArm;
	
	public ArmorStandProperty(){
		this.visible = true;
		this.gravity = true;
		this.basePlate = true;
		this.armsDisplayed = false;
		this.rotation = 0F;
		this.posBody = new Position(0, 0, 0);
		this.posHead = new Position(0, 0, 0);
		this.posLeftLeg = new Position(0, 0, 0);
		this.posRightLeg = new Position(0, 0, 0);
		this.posLeftArm = new Position(0, 0, 0);
		this.posRightArm = new Position(0, 0, 0);
		this.small = false;
	}
	
	public boolean isVisible() {
		return visible;
	}
	public ArmorStandProperty setVisible(boolean invisible) {
		this.visible = invisible;
		return this;
	}
	public boolean isGravity() {
		return gravity;
	}
	public ArmorStandProperty setGravity(boolean gravity) {
		this.gravity = gravity;
		return this;
	}
	public boolean isBasePlate() {
		return basePlate;
	}
	public ArmorStandProperty setBasePlate(boolean basePlate) {
		this.basePlate = basePlate;
		return this;
	}
	public boolean isArmsDisplayed() {
		return armsDisplayed;
	}
	public ArmorStandProperty setArmsDisplayed(boolean armsDisplayed) {
		this.armsDisplayed = armsDisplayed;
		return this;
	}
	public float getRotation() {
		return rotation;
	}
	public ArmorStandProperty setRotation(float rotation) {
		this.rotation = rotation;
		return this;
	}
	public Material getItemHand() {
		return itemHand;
	}
	public ArmorStandProperty setItemHand(Material itemHand) {
		this.itemHand = itemHand;
		return this;
	}
	public Material getItemHelmet() {
		return itemHelmet;
	}
	public ArmorStandProperty setItemHelmet(Material itemHelmet) {
		this.itemHelmet = itemHelmet;
		return this;
	}
	public Material getItemChestplate() {
		return itemChestplate;
	}
	public ArmorStandProperty setItemChestplate(Material itemChestplate) {
		this.itemChestplate = itemChestplate;
		return this;
	}
	public Material getItemLeggings() {
		return itemLeggings;
	}
	public ArmorStandProperty setItemLeggings(Material itemLeggings) {
		this.itemLeggings = itemLeggings;
		return this;
	}
	public Material getItemBoots() {
		return itemBoots;
	}
	public ArmorStandProperty setItemBoots(Material itemBoots) {
		this.itemBoots = itemBoots;
		return this;
	}
	public Position getPosHead() {
		return posHead;
	}
	public ArmorStandProperty setPosHead(Position posHead) {
		this.posHead = posHead;
		return this;
	}
	public Position getPosBody() {
		return posBody;
	}
	public ArmorStandProperty setPosBody(Position posBody) {
		this.posBody = posBody;
		return this;
	}
	public Position getPosLeftLeg() {
		return posLeftLeg;
	}
	public ArmorStandProperty setPosLeftLeg(Position posLeftLeg) {
		this.posLeftLeg = posLeftLeg;
		return this;
	}
	public Position getPosRightLeg() {
		return posRightLeg;
	}
	public ArmorStandProperty setPosRightLeg(Position posRightLeg) {
		this.posRightLeg = posRightLeg;
		return this;
	}
	public Position getPosLeftArm() {
		return posLeftArm;
	}
	public ArmorStandProperty setPosLeftArm(Position posLeftArm) {
		this.posLeftArm = posLeftArm;
		return this;
	}
	public Position getPosRightArm() {
		return posRightArm;
	}
	public ArmorStandProperty setPosRightArm(Position posRightArm) {
		this.posRightArm = posRightArm;
		return this;
	}
	
	public boolean isSmall() {
		return small;
	}

	public void setSmall(boolean small) {
		this.small = small;
	}

	@SuppressWarnings("deprecation")
	public String toJSON(){
		String s = "{";
		s+= "Invisible:" + (boolToNum(!isVisible())) + ",";
		s+= "Rotation:" + (int)getRotation() + ",";
		s+= "NoGravity:" + boolToNum(!isGravity()) + ",";
		s+= "ShowArms:" + boolToNum(isArmsDisplayed()) + ",";
		s+= "NoBasePlate:" + boolToNum(!isBasePlate()) + ",";
		s+= "Small:" + boolToNum(isSmall()) + ",";
		if(posHead != null || posBody != null || posLeftArm != null || posLeftLeg != null || posRightArm != null || posRightLeg != null){
			s+= "Pose:{";
			if(posBody != null){
				s+= "Body:[" + posBody.toJSON() + "],";
			}
			if(posLeftArm != null){
				s+= "LeftArm:[" + posLeftArm.toJSON() + "],";
			}
			if(posRightArm != null){
				s+= "RightArm:[" + posRightArm.toJSON() + "],";
			}
			if(posLeftLeg != null){
				s+= "LeftLeg:[" + posLeftLeg.toJSON() + "],";
			}
			if(posRightLeg != null){
				s+= "RightLeg:[" + posRightLeg.toJSON() + "],";
			}
			if(posHead != null){
				s+= "Head:[" + posHead.toJSON() + "],";
			}
			s = s.substring(0, s.length() - 1);
			s+="},";
		}
		if(itemBoots != null || itemChestplate != null || itemHand != null || itemHelmet != null || itemLeggings != null){
			s+= "Equipment:[";
			s+= "{";
			if(itemHand != null){
				s+= "id:" + itemHand.toString() + ",Count:1}";
			}
			s+= "},";
			s+= "{";
			if(itemBoots != null){
				s+= "id:" + itemBoots.toString() + ",Count:1";
			}
			s+= "},";
			s+= "{";
			if(itemLeggings != null){
				s+= "id:" + itemLeggings.toString() + ",Count:1";
			}
			s+= "},";
			s+= "{";
			if(itemChestplate != null){
				s+= "id:" + itemChestplate.toString() + ",Count:1";
			}
			s+= "},";
			s+= "{";
			if(itemHelmet != null){
				s+= "id:" + itemHelmet.getId() + ",";
			}
			s+= "}";
			s+="],";
		}
		s = s.substring(0, s.length() - 1);
		s+= "}";
		return s;
	}
	
	private String boolToNum(boolean bool){
		return (bool ? "1" : "0");
	}
	
}
