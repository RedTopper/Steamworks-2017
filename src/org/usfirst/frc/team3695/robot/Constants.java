package org.usfirst.frc.team3695.robot;

public class Constants {
	
	/**
	 * Motor PWM Ports
	 */
	public static final int 
			LEFT_MOTOR = 2,
			OTHER_LEFT_MOTOR = 3,
			RIGHT_MOTOR = 0,
			OTHER_RIGHT_MOTOR = 1,
			CLIMBER_MOTOR = 4,
			SHOOTER_MOTOR = 5,
			WINDOW_MOTOR1 = 6,
			WINDOW_MOTOR2 = 7;
	
	/**
	 * Motor Inverts
	 */
	public static final boolean 
			LEFT_MOTOR_INVERT = false,
			RIGHT_MOTOR_INVERT = false,
			SHOOTER_MOTOR_INVERT = false,
			ASCENDER_MOTOR_INVERT = true,
			WINDOW1_INVERT = false,
			WINDOW2_INVERT = true;
	
	/**
	 * PCM Ports
	 */
	public static final int 
			CLOSE_GEAR_FLAPS = 0,
			OPEN_GEAR_FLAPS = 1,
			CLOSE_GEAR_FEEDER_FLAPS = 4,
			OPEN_BALL_HOPPER = 7,
			CLOSE_BALL_HOPPER = 6;
	
	public static final double 
			SHOOTER_LIMIT = 1.00;
	
	/**
	 * Limit the Ascender speed so it does not destroy the gear-box. :)
	 */
	public static final double 
			ASCENDER_LIMIT = 0.8;
	
	public static final double 
			AGITATOR_LIMIT = 1.0;
}
