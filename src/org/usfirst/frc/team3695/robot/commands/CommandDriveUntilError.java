package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Camera;
import org.usfirst.frc.team3695.robot.enumeration.Direction;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemDrive;
import org.usfirst.frc.team3695.robot.util.Cross;
import org.usfirst.frc.team3695.robot.util.Util;

import edu.wpi.first.wpilibj.command.Command;

public class CommandDriveUntilError extends Command {
	public static final long ERROR_TIME = 500;
	public static final int TARGET_ERROR = 500;
	
	private final Direction direction;
	private final Cross object = new Cross("current error", (Camera.WIDTH / 2.0) + 20.0, -1);
	private final Cross setpoint = new Cross("error goal", Camera.WIDTH / 2.0, TARGET_ERROR / 3.0);
	private long time = 0;
	
	public CommandDriveUntilError(Direction direction) {
		requires(Robot.SUB_DRIVE);
		this.direction = direction;
		Robot.VISION.putCrosshair(object);
		Robot.VISION.putCrosshair(setpoint);
	}
	
    protected void initialize() {
		object.setEnabled(true);
		setpoint.setEnabled(true);
		time = System.currentTimeMillis() + ERROR_TIME;
	}

    protected void execute() {
    	object.setXY(object.getX(), Math.abs(Robot.SUB_DRIVE.getError() / 3.0));
		double speed = SubsystemDrive.ips2rpm(Util.getAndSetDouble("SPEED ERROR: Forward", 20.0));
		if(direction == Direction.BACKWARD) speed *= -1;
		Robot.SUB_DRIVE.driveDirect(speed, speed);
    }

    protected boolean isFinished() {
    	if(Math.abs(Robot.SUB_DRIVE.getError()) < TARGET_ERROR) {
    		time = System.currentTimeMillis() + ERROR_TIME;
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
}
