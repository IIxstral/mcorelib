package de.timc.mcorelib.armorstands;


public class Position {
	private float rotationX;
	private float rotationY;
	private float rotationZ;
	
	public Position(float rotationX, float rotationY, float rotationZ){
		this.rotationX = rotationX;
		this.rotationY = rotationY;
		this.rotationZ = rotationZ;
	}

	public float getRotationX() {
		return rotationX;
	}

	public void setRotationX(float rotationX) {
		this.rotationX = rotationX;
	}

	public float getRotationY() {
		return rotationY;
	}

	public void setRotationY(float rotationY) {
		this.rotationY = rotationY;
	}

	public float getRotationZ() {
		return rotationZ;
	}

	public void setRotationZ(float rotationZ) {
		this.rotationZ = rotationZ;
	}

	public String toJSON() {
		return (int)this.rotationX + "f," + (int)this.rotationY + "f," + (int)this.rotationZ + "f";
	}
	
	
}
