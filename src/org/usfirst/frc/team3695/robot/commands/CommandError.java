package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemDrive;
import org.usfirst.frc.team3695.robot.util.Cross;
import org.usfirst.frc.team3695.robot.util.Util;
import org.usfirst.frc.team3695.robot.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;

public class CommandError extends Command {
	public static final long START_READ_ERROR_DELAY = 500;
	private boolean finished = false;
	private final Cross object = new Cross("current error", (Vision.CAM_WIDTH / 2.0) + 20.0, -1);
	private final Cross setpoint = new Cross("error goal", Vision.CAM_WIDTH / 2.0, 240);
	long startTime = 0;
	
	public CommandError() {
		requires(Robot.SUB_DRIVE);
		Robot.VISION.putCrosshair(object);
		Robot.VISION.putCrosshair(setpoint);
	}
	
    protected void initialize() {
		object.setEnabled(true);
		setpoint.setEnabled(true);
		startTime = System.currentTimeMillis();
		finished = false;
    }

    protected void execute() {
    	object.setXY(object.getX(), Math.abs(Robot.SUB_DRIVE.getError()));
		if(startTime + START_READ_ERROR_DELAY < System.currentTimeMillis() && object.getY() > setpoint.getY()) {
			finished = true;
		} else {
			double speed = SubsystemDrive.ips2rpm(Util.getAndSetDouble("CAMERA: forward in-s", 20.0));
			Robot.SUB_DRIVE.driveDirect(speed, speed);
		}
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
    	Robot.SUB_DRIVE.driveDirect(0, 0);
    	object.setEnabled(false);
    	setpoint.setEnabled(false);
    }

    protected void interrupted() {
    	end();
    }
}
