package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandOpenBallHopper extends Command {
	private boolean isComplete = false;
	
    public CommandOpenBallHopper() {
        requires(Robot.subsystemBallHopper);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.subsystemBallHopper.toggle();
    	isComplete = true;
    }

    protected boolean isFinished() {
        return isComplete;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
