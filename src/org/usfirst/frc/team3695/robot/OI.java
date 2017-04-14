package org.usfirst.frc.team3695.robot;

import org.usfirst.frc.team3695.robot.commands.ButtonCommandBallHopper;
import org.usfirst.frc.team3695.robot.commands.ButtonCommandBoost;
import org.usfirst.frc.team3695.robot.commands.ButtonCommandCamera;
import org.usfirst.frc.team3695.robot.commands.ButtonCommandFlaps;
import org.usfirst.frc.team3695.robot.commands.ButtonCommandKillCompressor;
import org.usfirst.frc.team3695.robot.commands.CommandColor;
import org.usfirst.frc.team3695.robot.commands.CommandDistance;
import org.usfirst.frc.team3695.robot.commands.CommandDriveUntilError;
import org.usfirst.frc.team3695.robot.commands.CommandRotate;
import org.usfirst.frc.team3695.robot.enumeration.Autonomous;
import org.usfirst.frc.team3695.robot.enumeration.Camera;
import org.usfirst.frc.team3695.robot.enumeration.Direction;
import org.usfirst.frc.team3695.robot.util.Xbox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static final Joystick DRIVER = new Joystick(0);
	public static final Joystick OPERATOR = new Joystick(1);
	public OI(){
		
		/**
		 * Ball Loading
		 */
		Button ball = new JoystickButton(OPERATOR, Xbox.X);
		ball.toggleWhenActive(new ButtonCommandBallHopper());
		
		/**
		 * Switching the camera
		 */
		for(Camera cam : Camera.values()) {
			Button button = new JoystickButton(DRIVER, cam.button);
			button.toggleWhenActive(new ButtonCommandCamera(cam));
		}
		
		/**
		 * Sonic
		 */
		Button fast = new JoystickButton(DRIVER, Xbox.A);
		fast.whileHeld(new ButtonCommandBoost());
		
		/**
		 * Gear Flapping
		 */
		Button gear = new JoystickButton(OPERATOR, Xbox.RB);
		gear.toggleWhenActive(new ButtonCommandFlaps());
		
		/**
		 * To Compress, or Not To Compress. It is now an option.
		 */
		SmartDashboard.putData("Disable Compressor", new ButtonCommandKillCompressor());
		
		SmartDashboard.putData("AutoCamera", new CommandRotate(Autonomous.GEAR_RIGHT));
		SmartDashboard.putData("ErrorForward", new CommandDriveUntilError(Direction.FORWARD));
		SmartDashboard.putData("DriveDistance", new CommandDistance(12.0 * 6.0));
				
		/**
		 * And Dean said, Let there be light: and there were flashing LEDs
		 */
		SmartDashboard.putData("Red", new CommandColor(255, 0, 0));
		SmartDashboard.putData("Blue", new CommandColor(0, 0, 255));
	}
}
