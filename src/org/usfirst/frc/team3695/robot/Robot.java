
package org.usfirst.frc.team3695.robot;

import org.usfirst.frc.team3695.robot.commands.CommandAscend;
import org.usfirst.frc.team3695.robot.commands.CommandCompressor;
import org.usfirst.frc.team3695.robot.commands.CommandDrive;
import org.usfirst.frc.team3695.robot.commands.CommandShooter;
import org.usfirst.frc.team3695.robot.enumeration.Autonomous;
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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	// Auto
	private Autonomous autonomousCommand;
	
	// Choosers
	SendableChooser<Autonomous> autoChooser = new SendableChooser<>(); // should this be a sendable command chooser or a normal sendableChooser?
	
	// Commands
	Command commandComp;
	Command commanderDrive;
	Command commanderAscend;
	Command commanderSpin;
	
	// Static Subsystems
	public static SubsystemDrive subsystemDrive;
	public static SubsystemCompressor subsystemCompressor;
	public static SubsystemFlaps subsystemFlaps;
	public static SubsystemAscend subsystemAscend;
	public static SubsystemShooter subsystemShooter;
	public static OI oi;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Initialize Subsystems
		subsystemDrive = new SubsystemDrive();
		subsystemCompressor = new SubsystemCompressor();
		subsystemFlaps = new SubsystemFlaps();
		subsystemAscend = new SubsystemAscend();
		subsystemShooter = new SubsystemShooter();
		oi = new OI();
		
		// Initialize Commands
		commanderDrive = new CommandDrive();
		commanderAscend = new CommandAscend();
		commanderSpin = new CommandShooter();
		commandComp = new CommandCompressor();
		
		// autoChooser setup
		SmartDashboard.putData("Auto mode", autoChooser); // TODO add AutonomousPosition enum
		autoChooser.addDefault("Center", Autonomous.CENTER);
		autoChooser.addObject("Left", Autonomous.LEFT);
		autoChooser.addObject("Right", Autonomous.RIGHT);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = autoChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
