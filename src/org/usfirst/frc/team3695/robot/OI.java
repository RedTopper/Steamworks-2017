package org.usfirst.frc.team3695.robot;

import org.usfirst.frc.team3695.robot.commands.CommandFlaps;
import org.usfirst.frc.team3695.robot.commands.CommandIntimidate;
import org.usfirst.frc.team3695.robot.commands.CommandKillCompressor;
import org.usfirst.frc.team3695.robot.commands.CommandOpenBallHopper;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public OI(){
		/**
		 * Ball Loading
		 */
		Button openGear = new JoystickButton(Controller.OP_JOY(), 3);
		openGear.whenPressed(new CommandOpenBallHopper(true));
		
		/**
		 * Gear Flapping
		 */
		Button openFlap = new JoystickButton(Controller.OP_JOY(), Controller.OP_OPEN_GEAR);
		Button closeFlap = new JoystickButton(Controller.OP_JOY(), Controller.OP_RELEASE_GEAR);
		openFlap.whenPressed(new CommandFlaps(true));
		closeFlap.whenPressed(new CommandFlaps(false));
		
		/**
		 * Speed Gauge
		 */
		SmartDashboard.putNumber("Speed", (((Controller.DRIVE_JOY().getRawAxis(1) + (Controller.DRIVE_JOY().getRawAxis(2) - Controller.DRIVE_JOY().getRawAxis(3)))+
											(Controller.DRIVE_JOY().getRawAxis(5) + (Controller.DRIVE_JOY().getRawAxis(2) - Controller.DRIVE_JOY().getRawAxis(3))))/2));
		
		/**
		 * To Compress, or Not To Compress. It is now an option.
		 */
		SmartDashboard.putData("Disable Compressor", new CommandKillCompressor());
		
		/**
		 * Vroom vroom
		 */
		SmartDashboard.putData("Intimidate", new CommandIntimidate());
	}
}
