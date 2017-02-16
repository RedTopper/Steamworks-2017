package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandToggleBallHopper extends Command {
	boolean complete = false;
	/**
	 * 
	 */
    public CommandToggleBallHopper() {
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.subsystemFlaps);
        complete = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	complete = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!complete)
    		Robot.SUB_BALL_HOPPER.toggleFlaps();
    }

    protected boolean isFinished() {
        return complete;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
