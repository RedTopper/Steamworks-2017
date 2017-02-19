package org.usfirst.frc.team3695.robot;

import org.usfirst.frc.team3695.robot.enumeration.Autonomous;
import org.usfirst.frc.team3695.robot.enumeration.Camera;
import org.usfirst.frc.team3695.robot.enumeration.Video;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemAscend;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemBallHopper;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemCompressor;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemDrive;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemFlaps;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemShooter;
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
	public static final SubsystemDrive SUB_DRIVE = new SubsystemDrive();
	public static final SubsystemCompressor SUB_COMPRESSOR = new SubsystemCompressor();
	public static final SubsystemFlaps SUB_FLAPS = new SubsystemFlaps();
	public static final SubsystemAscend SUB_ASCEND = new SubsystemAscend();
	public static final SubsystemShooter SUB_SHOOTER = new SubsystemShooter();
	public static final SubsystemBallHopper SUB_BALL_HOPPER = new SubsystemBallHopper();
	
	//Output and Input
	public static final Grip GRIP = new Grip();
	public static final Vision VISION = new Vision();
	
	//Vars
	private Camera lastCam = Camera.FRONT;
	private Video lastVideo = Video.RAW;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		new OI();
		
		//Autonomous Chooser init
		SmartDashboard.putData("Auto mode", autoChooser);
		autoChooser.addDefault("Center", Autonomous.CENTER);
		autoChooser.addObject("Left", Autonomous.LEFT);
		autoChooser.addObject("Right", Autonomous.RIGHT);
		
		//Camera Chooser init
		chooserCamera.addDefault(Camera.FRONT.usb.getName(), Camera.FRONT);
		chooserCamera.addObject(Camera.REAR.usb.getName(), Camera.REAR);
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
		if (cam != null && (cam != lastCam || video != lastVideo)) {
			VISION.setCamera(cam, video);
			lastCam = cam;
			lastVideo = video;
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
