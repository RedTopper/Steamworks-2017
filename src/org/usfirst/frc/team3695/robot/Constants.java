package org.usfirst.frc.team3695.robot;

public class Constants {
	/**
	 * Non Boosting Drive Speed
	 */
	public static final double 
			NO_BOOST_MULTIPLIER = 0.5;
	
	/**
	 * Motor PWM Ports
	 */
	public static final int 
			LEFT_MOTOR = 2,
			OTHER_LEFT_MOTOR = 3,
			RIGHT_MOTOR = 0,
			OTHER_RIGHT_MOTOR = 1,
			CLIMBER_MOTOR = 4,
			SHOOTER_MOTOR = 5;
	
	/**
	 * Motor Inverts
	 */
	public static final boolean 
			LEFT_MOTOR_INVERT = false,
			RIGHT_MOTOR_INVERT = false,
			SHOOTER_MOTOR_INVERT = false;
	
	/**
	 * Joystick Assignments
	 */
	public static final int 
			DRIVER_STICK = 0,
			OPERATOR_STICK = 1;
	
	/**
	 * PCM Ports
	 */
	public static final int 
			CLOSE_GEAR_FLAPS = 0,
			OPEN_GEAR_FLAPS = 1,
			CLOSE_GEAR_FEEDER_FLAPS = 4;
	
	public static final double 
			SHOOTER_SPEED = 0.85;
	
	/**
	 * Limit the shooter speed so it does not destroy the gearbox. :)
	 */
	public static final double 
			SHOOTER_LIMIT = 0.4;
}
