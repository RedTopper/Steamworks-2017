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
        requires(Robot.SUB_FLAPS);
        this.opening = isOpening;
        this.complete = false;
    }

    protected void initialize() {
    	complete = false;
    }

    protected void execute() {
    	if (!complete) {
    		if (opening) Robot.SUB_FLAPS.openFlaps(); else Robot.SUB_FLAPS.closeFlaps();
    		complete = true;
    	}
    }

    protected boolean isFinished() {
        return complete;
    }

    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
