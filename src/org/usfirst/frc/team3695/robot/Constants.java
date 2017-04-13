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
		FEEDER_CONVEYOR_LEFT = 9,
		FEEDER_CONVEYOR_RIGHT = 8,
		FEEDER_ROTOR_LEFT = 6,
		FEEDER_ROTOR_RIGHT = 7;
	/** COMP. BOT
	public static final int 
			LEFT_MOTOR = 2,
			OTHER_LEFT_MOTOR = 3,
			RIGHT_MOTOR = 0,
			OTHER_RIGHT_MOTOR = 1,
			CLIMBER_MOTOR = 4,
			SHOOTER_MOTOR = 5,
			WINDOW_MOTOR1 = 6,
			WINDOW_MOTOR2 = 7,
			SERVO_PORT = 0;
	**/
	
	/**
	 * Motor Inverts
	 */
	public static final boolean 
			LEFT_MOTOR_INVERT = true,
			RIGHT_MOTOR_INVERT = false,
			SHOOTER_MOTOR_INVERT = false,
			ASCENDER_MOTOR_INVERT = false,
			FEEDER_CONVEYOR_LEFT_INVERT = false,
			FEEDER_CONVEYOR_RIGHT_INVERT = false,
			FEEDER_ROTOR_LEFT_INVERT = true,
			FEEDER_ROTOR_RIGHT_INVERT = false;	
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
			INTIMIDATION_SPEED = 0.5;
	
	/**
	 * Limit Shooter to not hit ceiling
	 */
	public static final double 
			SHOOTER_LIMIT = 0.8,
			SHOOTER_TARGET_VELOCITY_RPM = 3950.0;
	
	/**
	 * Limit the Ascender speed so it does not destroy the gear-box. :)
	 */
	public static final double 
			ASCENDER_LIMIT = 1.0;
	
	/**
	 * Limit Agitators (Not Really)
	 */
	public static final double 
			AGITATOR_LIMIT = 1.0;
	
	/**
	 * RGB LEDS
	 */
	public static final int 
			RED_PWM = 1,
			GREEN_PWM = 2,
			BLUE_PWM = 3;
	
	/**
	 * Feeder Limits (The Conveyor System iis Ghetto-Rigged using 2 different motors, one of which is supposedly faster. Will Need To Test)
	 */
	public static final double FEEDER_CONVEYOR_LEFT_LIMIT = 1.0,
			FEEDER_CONVEYOR_RIGHT_LIMIT = 1.0,
			FEEDER_ROTOR_LIMIT = 1.0;
}
