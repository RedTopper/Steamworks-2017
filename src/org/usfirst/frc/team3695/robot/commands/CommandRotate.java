package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Camera;
import org.usfirst.frc.team3695.robot.enumeration.Video;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemDrive;
import org.usfirst.frc.team3695.robot.util.Util;
import org.usfirst.frc.team3695.robot.vision.PIDVision;
import org.usfirst.frc.team3695.robot.vision.Vision;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandRotate extends PIDCommand {
	
	private PIDVision vision;
	private long time = Long.MAX_VALUE;
	public static final long TIME_WAIT = 2000;
	
	public CommandRotate() {
		super(1.0, 1.0, 1.0); //Will be set in initialize();
		requires(Robot.SUB_DRIVE);
		vision = new PIDVision();
	}
	
    protected void initialize() {
    	double p = Util.getAndSetDouble("PID: P", 1.0);
    	double i = Util.getAndSetDouble("PID: I", 1.0);
    	double d = Util.getAndSetDouble("PID: D", 1.0);
    	getPIDController().setPID(p, i, d);
    	getPIDController().setPercentTolerance(5.0);
		setInputRange(0, Vision.CAM_WIDTH);
		setSetpoint(Vision.CAM_WIDTH / 2);
		time = Long.MAX_VALUE;
		Robot.VISION.setCamera(Camera.FRONT, Video.THRESHHOLD);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if(!getPIDController().onTarget()) {
    		time = System.currentTimeMillis() + TIME_WAIT;
    	}
        return time < System.currentTimeMillis();
    }

    protected void end() {
    	Robot.SUB_DRIVE.directDrive(0, 0);
    }

    protected void interrupted() {
    	end();
    }

	protected double returnPIDInput() {
		return vision.pidGet();
	}

	protected void usePIDOutput(double output) {
		double target = output * SubsystemDrive.ips2rpm(Util.getAndSetDouble("CAMERA: Targeting in-s", 1.0));
		double blind = SubsystemDrive.ips2rpm(Util.getAndSetDouble("CAMERA: Blind in-s", 1.0));
		
		SmartDashboard.putNumber("PID", output);
		SmartDashboard.putBoolean("Target Found", vision.canSee());
		if(vision.canSee()) {
			Robot.SUB_DRIVE.directDrive(target, -target);
		} else {
			Robot.SUB_DRIVE.directDrive(blind, -blind);
		}
	}
}
