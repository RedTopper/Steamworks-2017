package org.usfirst.frc.team3695.robot.util;

import com.ctre.CANTalon;

public class TalonPID {
	private final CANTalon talon;
	private final String name;
	
	double p = 0.0, i = 0.0, d = 0.0;
	
	/**
	 * Update a talon's PID only when needed so the CAN bus does not get overloaded.
	 * @param talon The talon (master) to update from 
	 * @param name The name of the talon (readability reasons)
	 */
	public TalonPID(CANTalon talon, String name) {
		this.talon = talon;
		this.name = name;
	}
	
	/**
	 * Update all PID variables from smart dash if needed.
	 */
	public void update() {
    	double new_p = Util.getAndSetDouble("PID " + name + ": P", 0.0);
    	double new_i = Util.getAndSetDouble("PID " + name + ": I", 0.0);
    	double new_d = Util.getAndSetDouble("PID " + name + ": D", 0.0);
    	
    	if(p != new_p) {
    		talon.setP(new_p);
    		p = new_p;
    	}
    	if(i != new_i) {
    		talon.setI(new_i);
    		i = new_i;
    	}
    	if(d != new_d) {
    		talon.setD(new_d);
    		d = new_d;
    	}
	}
}
