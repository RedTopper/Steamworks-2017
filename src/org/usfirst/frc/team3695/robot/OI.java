package org.usfirst.frc.team3695.robot;

import org.usfirst.frc.team3695.robot.commands.ButtonCommandBallHopper;
import org.usfirst.frc.team3695.robot.commands.ButtonCommandCamera;
import org.usfirst.frc.team3695.robot.commands.ButtonCommandFlaps;
import org.usfirst.frc.team3695.robot.commands.ButtonCommandKillCompressor;
import org.usfirst.frc.team3695.robot.commands.ButtonCommandShooter;
import org.usfirst.frc.team3695.robot.commands.CommandDistance;
import org.usfirst.frc.team3695.robot.commands.CommandDriveUntilError;
import org.usfirst.frc.team3695.robot.commands.CommandRotate;
import org.usfirst.frc.team3695.robot.enumeration.Autonomous;
import org.usfirst.frc.team3695.robot.enumeration.Direction;
import org.usfirst.frc.team3695.robot.enumeration.Feeder;
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
		Button cam = new JoystickButton(DRIVER, Xbox.Y);
		cam.toggleWhenActive(new ButtonCommandCamera());
		
		/**
		 * 
		 * Gear Flapping
		 */
		Button gear = new JoystickButton(OPERATOR, Xbox.RB);
		gear.toggleWhenActive(new ButtonCommandFlaps());
		
		/**
		 * Ball shooting
		 */
		Button leftShoot = new JoystickButton(OPERATOR, Xbox.LSTICK);
		leftShoot.whileHeld(new ButtonCommandShooter(Direction.FORWARD, Feeder.LEFT));
		Button rightShoot = new JoystickButton(OPERATOR, Xbox.RSTICK);
		rightShoot.whileHeld(new ButtonCommandShooter(Direction.FORWARD, Feeder.RIGHT));
		Button kill = new JoystickButton(OPERATOR, Xbox.B);
		kill.whileHeld(new ButtonCommandShooter(Direction.BACKWARD, Feeder.NOT));
		
		
		/**
		 * To Compress, or Not To Compress. It is now an option.
		 */
		SmartDashboard.putData("Disable Compressor", new ButtonCommandKillCompressor());
		
		SmartDashboard.putData("AutoCamera", new CommandRotate(Autonomous.GEAR_LEFT));
		SmartDashboard.putData("ErrorForward", new CommandDriveUntilError(Direction.FORWARD));
		SmartDashboard.putData("DriveDistance", new CommandDistance(12.0 * 6.0));
	}
}
