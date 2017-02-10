package org.usfirst.frc.team3695.robot;

import org.usfirst.frc.team3695.robot.commands.CommandKillCompressor;
import org.usfirst.frc.team3695.robot.commands.CommandOpenBallHopper;
import org.usfirst.frc.team3695.robot.commands.CommandFlaps;

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
		 * To Compress, or Not To Compress. It is now an option.
		 */
		SmartDashboard.putData("Disable Compressor", new CommandKillCompressor());
		
		/**
		 * Gear Flapping
		 */
		Button openFlap = new JoystickButton(Controller.OP_JOY(), Controller.OP_OPEN_GEAR);
		Button closeFlap = new JoystickButton(Controller.OP_JOY(), Controller.OP_RELEASE_GEAR);
		openFlap.whenPressed(new CommandFlaps(true));
		closeFlap.whenPressed(new CommandFlaps(false));
		
		/**
		 * Ball Loading
		 */
		Button openGear = new JoystickButton(Controller.OP_JOY(), 3);
		openGear.whenPressed(new CommandOpenBallHopper());
	}
}
