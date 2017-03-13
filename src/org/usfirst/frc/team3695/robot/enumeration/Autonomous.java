package org.usfirst.frc.team3695.robot.enumeration;

public enum Autonomous {
	NOTHING("Do Nothing"),
	FORWARD_8_FT("Just drive 8 feet"),
	GEAR_LEFT("Gear: Left"),
	GEAR_CENTER("Gear: Center"),
	GEAR_CENTER_BASELINE_LEFT("Gear: Center AND Reach LEFT baseline"),
	GEAR_CENTER_BASELINE_RIGHT("Gear: Center AND Reach RIGHT baseline"),
	GEAR_RIGHT("Gear: Right"),
	GEAR_RIGHT_SHOOT("Gear: Right AND Fire!");
	
	private final String name;
	Autonomous(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
