package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Controller;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CommandDrive extends Command {
	

    public CommandDrive() {
        requires(Robot.SUB_DRIVE);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.SUB_DRIVE.dualStickDrive(Controller.DRIVE_JOY());
    	SmartDashboard.putNumber("Left Vel", Robot.SUB_DRIVE.getLeftVelocity());
    	SmartDashboard.putNumber("Right Vel", Robot.SUB_DRIVE.getRightVelocity());
    	SmartDashboard.putNumber("MAXIMUM SPEED", Robot.SUB_DRIVE.getMaxRPMS());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
