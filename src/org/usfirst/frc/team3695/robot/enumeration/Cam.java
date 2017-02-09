package org.usfirst.frc.team3695.robot.enumeration;

public enum Cam {
	FRONT_CAM(0), REAR_CAM(1);
	
	
	private int dev;
	private Cam(int i) { 
		this.dev = i;
	}
	public int getDev() {
		return this.dev;
	}
}
