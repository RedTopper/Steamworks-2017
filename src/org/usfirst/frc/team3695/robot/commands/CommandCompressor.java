package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Code that kills Pnuematics until interruption
 */
public class CommandCompressor extends Command {

    public CommandCompressor() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.subsystemCompressor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.subsystemCompressor.setState(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.subsystemCompressor.setState(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
