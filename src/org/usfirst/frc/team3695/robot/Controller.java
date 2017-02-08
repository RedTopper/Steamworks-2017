package org.usfirst.frc.team3695.robot;

import edu.wpi.first.wpilibj.Joystick;
/**
 * Code Stolen From AJ
 * @author AAAAaRON WATERS
 */
public class Controller {
	private static final Joystick driver = new Joystick(Constants.DRIVER_STICK);
	private static final Joystick operator = new Joystick(Constants.OPERATOR_STICK);

	/** XBox "B Button" */
	public static final int DRIVE_BOOST = 2;
	/** XBox "Y Button" */
	public static final int DRIVE_REAR_CAM = 4;
	/** XBox "X Button" */
	public static final int DRIVE_PROCESSED_CAM = 3;
	/** The degree amount the POV must be to rotate the robot left for auto targeting. */
	public static final int DRIVE_TARGET_LEFT = 270;
	/** The degree amount the POV must be to rotate the robot right for auto targeting. */
	public static final int DRIVE_TARGET_RIGHT = 90;
	
	/** XBox "Left Bumper" */
	public static final int OP_OPEN_GEAR = 5;
	/** XBox "Right Bumper" */
	public static final int OP_RELEASE_GEAR = 6;
	/** XBox "A Button" */
	public static final int OP_FIRE_ARM = 1;
	/** XBox "B Button" */
	public static final int OP_RESET_ARM = 2;
	/** XBox "Left Bumper" */
	public static final int OP_PISTON_UP = 5;
	/** XBox "Right Bumper" */
	public static final int OP_PISTON_DOWN = 6;
	/** The degree amount the POV must be to move the bucket UP. */
	public static final int OP_BUCKET_UP_POV_DEG = 0;
	/** The degree amount the POV must be to move the bucket DOWN. */
	public static final int OP_BUCKET_DOWN_POV_DEG = 180;

	//Raw joystick values.
	/**
	 * Use this to obtain the joystick for the driver.
	 * @return The driver joystick
	 */
	public static Joystick DRIVE_JOY() {
		return driver;
	}
	
	/**
	 * Use this to obtain the joystick for the operator.
	 * @return The operator joystick
	 */
	public static Joystick OP_JOY() {
		return operator;
}
}
