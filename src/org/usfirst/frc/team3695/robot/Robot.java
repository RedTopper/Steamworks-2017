
package org.usfirst.frc.team3695.robot;

import org.usfirst.frc.team3695.robot.commands.CommandAscend;
import org.usfirst.frc.team3695.robot.commands.CommandKillCompressor;
import org.usfirst.frc.team3695.robot.commands.CommandDrive;
import org.usfirst.frc.team3695.robot.commands.CommandShooter;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemAscend;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemCompressor;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemDrive;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemFlaps;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemShooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * Team 3695 Main Robot Function
 * @author 3695
 *
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	
	//Subsystems
	public static SubsystemDrive subsystemDrive = new SubsystemDrive();
	public static SubsystemCompressor subsystemCompressor = new SubsystemCompressor();
	public static SubsystemFlaps subsystemFlaps = new SubsystemFlaps();
	public static SubsystemAscend subsystemAscend = new SubsystemAscend();
	public static SubsystemShooter subsystemShooter = new SubsystemShooter();
	
	//Commands
	Command commandComp = new CommandKillCompressor();
	Command commanderDrive = new CommandDrive();
	Command commanderAscend = new CommandAscend();
	Command commanderShoot = new CommandShooter();
	
	//Choosers
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		oi = new OI();
		
		//Chooser init
		//chooser.addDefault("Default Auto", new ExampleCommand());
		//chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * Initializes autonomous control with a selection on the driver dash
	 */
	public void autonomousInit() {
		//autonomousCommand = chooser.getSelected();
		//if (autonomousCommand != null)
		//	autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * This function is called once to initialize operator control
	 */
	public void teleopInit() {
		//if (autonomousCommand != null)
		//	autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
