package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.util.Util;
import org.usfirst.frc.team3695.robot.vision.PIDVision;
import org.usfirst.frc.team3695.robot.vision.Vision;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandRotate extends PIDCommand {
	
	private PIDVision vision;
	
	public CommandRotate() {
		super(0, 0, 0); //Will be set in initialize();
		requires(Robot.SUB_DRIVE);
		vision = new PIDVision();
	}
	
    protected void initialize() {
    	double p = Util.getAndSetDouble("PID: P", 0.0);
    	double i = Util.getAndSetDouble("PID: I", 0.0);
    	double d = Util.getAndSetDouble("PID: D", 0.0);
    	getPIDController().setPID(p, i, d);
		setInputRange(0, Vision.CAM_WIDTH);
		setSetpoint(Vision.CAM_WIDTH / 2);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }

	protected double returnPIDInput() {
		return vision.pidGet();
	}

	protected void usePIDOutput(double output) {
		output = output * Util.getAndSetDouble("CAM: Can See Speed", 0.5);
		double no = Util.getAndSetDouble("CAM: Cant See Speed", 0.35);
		SmartDashboard.putNumber("PID", output);
		if(vision.canSee()) {
			Robot.SUB_DRIVE.directDrive(output, -output);
		} else {
			Robot.SUB_DRIVE.directDrive(no, -no);
		}
	}
}
