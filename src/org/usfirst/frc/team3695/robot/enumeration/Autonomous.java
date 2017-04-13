package org.usfirst.frc.team3695.robot.enumeration;

public enum Autonomous {
	NOTHING("Do Nothing"),
	FORWARD("Just drive 8 feet"),
	GEAR_LEFT("Gear: Left"),
	GEAR_LEFT_RUN("Gear: Left and RUN AWAY!"),
	GEAR_CENTER("Gear: Center"),
	GEAR_RIGHT("Gear: Right"),
	GEAR_RIGHT_RUN("Gear: Right and RUN AWAY!");
	
	private final String name;
	Autonomous(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
