package org.usfirst.frc.team3695.robot;

public class Constants {
	/**
	 * Non Boosting Drive Speed
	 */
	public static final double NO_BOOST_MULTIPLIER = 0.5;
	/**
	 * Motor PWM Ports
	 */
	public static int LEFT_MOTOR = 2,
			OTHER_LEFT_MOTOR = 3,
			RIGHT_MOTOR = 0,
			OTHER_RIGHT_MOTOR = 1;
	/**
	 * Motor Inverts
	 */
	public static boolean LEFT_MOTOR_INVERT = false,
			RIGHT_MOTOR_INVERT = false;
	
	/**
	 * Joystick Assignments
	 */
	public static int DRIVER_STICK = 0,
			OPERATOR_STICK = 1;
	
	/**
	 * PCM Ports
	 */
	public static int CLOSE_GEAR_FLAPS = 0,
			OPEN_GEAR_FLAPS = 1,
			CLOSE_GEAR_FEEDER_FLAPS = 4;
}
