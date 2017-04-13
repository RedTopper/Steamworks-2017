package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Autonomous;
import org.usfirst.frc.team3695.robot.enumeration.Camera;
import org.usfirst.frc.team3695.robot.enumeration.Video;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemDrive;
import org.usfirst.frc.team3695.robot.util.Cross;
import org.usfirst.frc.team3695.robot.util.Util;
import org.usfirst.frc.team3695.robot.vision.PIDVision;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandRotate extends PIDCommand {
	
	public static final long TIME_WAIT = 600;
	public static final double TARGET = (Camera.WIDTH / 2.0) - 10.0;

	private final Cross object = new Cross("object", -1, (Camera.HEIGHT / 2.0) + 20.0);
	private final Cross setpoint = new Cross("setpoint", TARGET, Camera.HEIGHT / 2.0);
	private final Autonomous auto;
	private volatile boolean canSee = false;
	private long time = Long.MAX_VALUE;
	private PIDVision vision;
	
	
	public CommandRotate(Autonomous auto) {
		super(0.0, 0.0, 0.0); //Will be set in initialize();
		requires(Robot.SUB_DRIVE);
		vision = new PIDVision();
		Robot.VISION.putCrosshair(object);
		Robot.VISION.putCrosshair(setpoint);
		this.auto = auto;
	}
	
    protected void initialize() {
    	Robot.VISION.setCamera(Camera.FRONT, Video.THRESHHOLD);
    	double p = Util.getAndSetDouble("PID CAMERA: P", 0.0);
    	double i = Util.getAndSetDouble("PID CAMERA: I", 0.0);
    	double d = Util.getAndSetDouble("PID CAMERA: D", 0.0);
    	getPIDController().reset();
    	getPIDController().setPID(p, i, d);
    	getPIDController().setPercentTolerance(5.0);
		setInputRange(0, Camera.WIDTH);
		setSetpoint(setpoint.getX());
		this.time = Long.MAX_VALUE;
		this.canSee = false;
		object.setEnabled(true);
		setpoint.setEnabled(true);
    }

    protected void execute() {
		double blind = SubsystemDrive.ips2rpm(Util.getAndSetDouble("SPEED CAMERA: Blind Inches", 18.0));
		if(auto == Autonomous.GEAR_LEFT || auto == Autonomous.GEAR_LEFT_RUN) blind *= -1.0;
		SmartDashboard.putBoolean("Target Found", canSee);
		
		//update canSee here too, in case usePIDOutput is ever fixed.
		canSee = vision.canSee();
		if(canSee) {
			getPIDController().enable();
			object.setEnabled(true);
		} else {
			getPIDController().reset();
			object.setEnabled(false);
			Robot.SUB_DRIVE.driveDirect(-blind, blind);
		}
    }

    protected boolean isFinished() {
    	if(!getPIDController().onTarget() || !canSee) {
    		time = System.currentTimeMillis() + TIME_WAIT;
    	}
        return time < System.currentTimeMillis();
    }

    protected void end() {
    	Robot.SUB_DRIVE.driveDirect(0, 0);
    	object.setEnabled(false);
    	setpoint.setEnabled(false);
    }

    protected void interrupted() {
    	end();
    }

	protected double returnPIDInput() {
		double x = vision.pidGet();
		object.setXY(x, object.getY());
		return x;
	}

	//Note to self: for some reason this method is called all the time, even
	//when the PIDController is disabled. Why? No idea.
	protected void usePIDOutput(double output) {
		canSee = vision.canSee();
		if(canSee) {
			double target = output * SubsystemDrive.ips2rpm(Util.getAndSetDouble("SPEED CAMERA: Targeting Inches", 30.0));
			Robot.SUB_DRIVE.driveDirect(-target, target);
			SmartDashboard.putNumber("PID", output);
		}
	}
}
