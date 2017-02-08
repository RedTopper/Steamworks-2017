package org.usfirst.frc.team3695.robot.vision;

public enum Camera {
	FRONT(0), REAR(1);
	
	public final int dev;
	private Camera(int dev) {this.dev = dev;}
}
