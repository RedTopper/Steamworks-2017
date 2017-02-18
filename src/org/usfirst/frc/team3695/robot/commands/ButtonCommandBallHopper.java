package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ButtonCommandBallHopper extends Command {

    public ButtonCommandBallHopper() {
        requires(Robot.SUB_BALL_HOPPER);
    }

    protected void initialize() {
    	Robot.SUB_BALL_HOPPER.openFlaps();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.SUB_BALL_HOPPER.closeFlaps();
    }

    protected void interrupted() {
    	end();
    }
}
