package org.usfirst.frc.team3695.robot;

import org.usfirst.frc.team3695.robot.auto.CommandGroupAuto;
import org.usfirst.frc.team3695.robot.enumeration.Autonomous;
import org.usfirst.frc.team3695.robot.enumeration.Camera;
import org.usfirst.frc.team3695.robot.enumeration.Video;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemAscend;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemBling;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemCompressor;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemDrive;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemFuelFlaps;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemGearFlaps;
import org.usfirst.frc.team3695.robot.vision.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
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
	//Choosers
	SendableChooser<Autonomous> autoChooser = new SendableChooser<>();
	SendableChooser<Camera> chooserCamera = new SendableChooser<>();
	SendableChooser<Video> chooserVideo = new SendableChooser<>();
	
	//Subsystems
	public static SubsystemDrive SUB_DRIVE;
	public static SubsystemCompressor SUB_COMPRESSOR;
	public static SubsystemGearFlaps SUB_GEAR_FLAPS;
	public static SubsystemFuelFlaps SUB_FUEL_FLAPS;
	public static SubsystemAscend SUB_ASCEND;
	public static SubsystemBling SUB_BLINGY;
	
	//Output and Input
	public static final Grip GRIP = new Grip();
	public static final Vision VISION = new Vision();
	
	//Auto
	private CommandGroupAuto auto;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		
		//Instantiate subsystems
		SUB_DRIVE = new SubsystemDrive();
		SUB_COMPRESSOR = new SubsystemCompressor();
		SUB_GEAR_FLAPS = new SubsystemGearFlaps();
		SUB_FUEL_FLAPS = new SubsystemFuelFlaps();
		SUB_ASCEND = new SubsystemAscend();
		SUB_BLINGY = new SubsystemBling();
		
		//Operator Interface
		new OI();
		
		//Autonomous Chooser init
		autoChooser.addDefault(Autonomous.NOTHING.toString(), Autonomous.NOTHING);
		for(int i = 1; i < Autonomous.values().length; i++) {
			autoChooser.addObject(Autonomous.values()[i].toString(), Autonomous.values()[i]);
		}
		SmartDashboard.putData("Auto Mode", autoChooser);
		
		//Camera Chooser init
		chooserCamera.addDefault(Camera.FRONT.getUSB().getName(), Camera.FRONT);
		chooserCamera.addObject(Camera.REAR.getUSB().getName(), Camera.REAR);
		SmartDashboard.putData("Camera", chooserCamera);
		
		//Video mode chooser (ex to view GRIP)
		chooserVideo.addDefault("Raw", Video.RAW);
		chooserVideo.addObject("Low Exposure", Video.LOW_EXPOSURE);
		chooserVideo.addObject("Threshhold", Video.THRESHHOLD);
		SmartDashboard.putData("Video Mode", chooserVideo);
		
		VISION.start();
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
		Video video = chooserVideo.getSelected();
		if (cam != null && video != null) {
			VISION.setCamera(cam, video);
		}
	}

	/**
	 * Initializes autonomous control with a selection on the driver dash
	 */
	public void autonomousInit() {
		if(autoChooser.getSelected() != null) {
			auto = new CommandGroupAuto(autoChooser.getSelected());
			auto.start();
		}
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
		if (auto != null)
			auto.cancel();
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
