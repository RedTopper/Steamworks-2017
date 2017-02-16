package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandOpenBallHopper extends Command {
	boolean complete = false;
	boolean opening;
	/**
	 * 
	 */
    public CommandOpenBallHopper(boolean isOpening) {
        requires(Robot.SUB_FLAPS);
        this.opening = isOpening;
        this.complete = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	complete = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!complete)
    		if (opening){
    			Robot.SUB_BALL_HOPPER.openFlaps();
    			//Robot.subsystemFlaps.toggleFlaps();
    			complete = true;
    		} else {
    			Robot.SUB_BALL_HOPPER.closeFlaps();
    			//Robot.subsystemFlaps.toggleFlaps();
    			complete = true;
    		}
    }

    protected boolean isFinished() {
        return complete;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
