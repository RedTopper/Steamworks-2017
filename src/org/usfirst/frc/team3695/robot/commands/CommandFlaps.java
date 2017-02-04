package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandFlaps extends Command {
	boolean complete = false;
	boolean opening;
	/**
	 * 
	 */
    public CommandFlaps(boolean isOpening) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.subsystemFlaps);
        isOpening = this.opening;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!complete)
    		if (opening){
    			Robot.subsystemFlaps.openFlaps();
    			complete = true;
    		} else {
    			Robot.subsystemFlaps.closeFlaps();
    			complete = true;
    		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return complete;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
