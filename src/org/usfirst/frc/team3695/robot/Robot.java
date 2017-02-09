
package org.usfirst.frc.team3695.robot;

import org.usfirst.frc.team3695.robot.commands.CommandAscend;
import org.usfirst.frc.team3695.robot.commands.CommandDrive;
import org.usfirst.frc.team3695.robot.commands.CommandKillCompressor;
import org.usfirst.frc.team3695.robot.commands.CommandShooter;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemAscend;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemCompressor;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemDrive;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemFlaps;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemShooter;
import org.usfirst.frc.team3695.robot.vision.Camera;
import org.usfirst.frc.team3695.robot.vision.Video;
import org.usfirst.frc.team3695.robot.vision.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Team 3695 Main Robot Function
 * @author 3695
 *
 */
public class Robot extends IterativeRobot {

	//Output and Input
	public static OI oi;
	
	//Vars
	private Camera lastCam = Camera.FRONT;
	private Vision visionThread = new Vision();
	
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
	SendableChooser<Camera> chooserCamera = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		
		visionThread.start();
		
		//Chooser init
		chooserCamera.addDefault(Camera.FRONT.usb.getName(), Camera.FRONT);
		chooserCamera.addObject(Camera.REAR.usb.getName(), Camera.REAR);
		SmartDashboard.putData("Camera", chooserCamera);
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
		Camera cam = chooserCamera.getSelected();
		if (cam != null && cam != lastCam) {
			visionThread.setCamera(cam, Video.ROBOT);
			lastCam = cam;
		}
	}

	/**
	 * Initializes autonomous control with a selection on the driver dash
	 */
	public void autonomousInit() {

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
