package org.usfirst.frc.team3695.robot.util;

import org.usfirst.frc.team3695.robot.subsystems.SubsystemDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class TalonPID {
	private final CANTalon[] talons;
	private final String name;
	private TalonControlMode lastMode = null;
	
	/**
	 * Contains:
	 * [0] = P
	 * [1] = I
	 * [2] = D
	 * [3] = cruise
	 * [4] = acceleration 
	 */
	private double[] old = new double[5], newer = new double[5];
	public static final int
			P = 0,
			I = 1,
			D = 2,
			CRUISE = 3,
			ACCELERATION = 4;
	
	/**
	 * Update a talon's PID only when needed so the CAN bus does not get overloaded.
	 * @param talons The talons (master) to update pid values.
	 * @param name The name of the talon (readability reasons)
	 */
	public TalonPID(CANTalon[] talons, String name) {
		this.talons = talons;
		this.name = name;
	}
	
	/**
	 * Update all PID variables from smart dash if needed.
	 * @param motionmagic 
	 */
	public void update(TalonControlMode mode) {
		mode(mode);
		switch(mode) {
		case MotionMagic:
			newer[P] = Util.getAndSetDouble("PID " + name + " MOTION: P", 0.0);
			newer[I] = Util.getAndSetDouble("PID " + name + " MOTION: I", 0.0);
			newer[D] = Util.getAndSetDouble("PID " + name + " MOTION: D", 0.0);
			newer[CRUISE] = Util.getAndSetDouble("CRUISE " + name + ": Inches", 0.0);
			newer[ACCELERATION]  = Util.getAndSetDouble("ACCEL " + name + ": Inches", 0.0);
			break;
		case Speed:
			newer[P] = Util.getAndSetDouble("PID " + name + " SPEED: P", 0.0);
			newer[I] = Util.getAndSetDouble("PID " + name + " SPEED: I", 0.0);
			newer[D] = Util.getAndSetDouble("PID " + name + " SPEED: D", 0.0);
			newer[CRUISE] = 0.0;
			newer[ACCELERATION] = 0.0;
			break;
		default:
		case PercentVbus:
			newer[P] = 0.0;
			newer[I] = 0.0;
			newer[D] = 0.0;
			newer[CRUISE] = 0.0;
			newer[ACCELERATION] = 0.0;
			break;
		}
		send();
	}
	
	private void send() {
		boolean different = false;
		for(int i = 0; i < old.length; i++) {
			if(old[i] != newer[i]) {
				different = true;
				break;
			}
		}
		if(different) {
			for(int i = 0; i < old.length; i++) {
				old[i] = newer[i];
			}
			for(CANTalon talon : talons) {
				talon.setPID(newer[P], newer[I], newer[D]);
				//talon.setF(0.25);
				talon.setMotionMagicCruiseVelocity(SubsystemDrive.ips2rpm(newer[CRUISE]));
				talon.setMotionMagicAcceleration(SubsystemDrive.ips2rpm(newer[ACCELERATION]));
			}
		}
	}

	private void mode(TalonControlMode mode) {
		if(mode != lastMode) {
			for(CANTalon talon : talons) {
				talon.changeControlMode(mode);
			}
	    	lastMode = mode;
		}
	}
}
