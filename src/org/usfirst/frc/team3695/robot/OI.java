package org.usfirst.frc.team3695.robot;

import org.usfirst.frc.team3695.robot.commands.CommandFlaps;
import org.usfirst.frc.team3695.robot.commands.CommandIntimidate;
import org.usfirst.frc.team3695.robot.commands.CommandKillCompressor;
import org.usfirst.frc.team3695.robot.commands.CommandOpenBallHopper;
import org.usfirst.frc.team3695.robot.commands.CommandRotate;
import org.usfirst.frc.team3695.robot.enumeration.Xbox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private static final Joystick DRIVER = new Joystick(0);
	private static final Joystick OPERATOR = new Joystick(1);
	public OI(){
		
		/**
		 * Ball Loading
		 */
		Button openGear = new JoystickButton(OPERATOR, Xbox.X);
		openGear.whenPressed(new CommandOpenBallHopper(true));
		
		/**
		 * Gear Flapping
		 */
		Button openFlap = new JoystickButton(OPERATOR, Xbox.RB);
		Button closeFlap = new JoystickButton(OPERATOR, Xbox.LB);
		openFlap.whenPressed(new CommandFlaps(true));
		closeFlap.whenPressed(new CommandFlaps(false));
		
		/**
		 * Open/Closed Funnel
		 */
		SmartDashboard.putBoolean("Funnel is Open", Robot.SUB_FLAPS.getOpen());
				
		/**
		 * To Compress, or Not To Compress. It is now an option.
		 */
		SmartDashboard.putData("Disable Compressor", new CommandKillCompressor());
		
		/**
		 * Vroom Vroom
		 */
		SmartDashboard.putData("Intimidate", new CommandIntimidate());
		
		SmartDashboard.putData("AutoCamera", new CommandRotate());
	}
}
