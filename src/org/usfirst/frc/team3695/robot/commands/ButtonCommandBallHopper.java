package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Commands the ball hopper flaps.
 */
public class ButtonCommandBallHopper extends Command {

    public ButtonCommandBallHopper() {
        requires(Robot.SUB_FUEL_FLAPS);
    }

    protected void initialize() {
    	Robot.SUB_FUEL_FLAPS.openFlaps();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.SUB_FUEL_FLAPS.closeFlaps();
    }

    protected void interrupted() {
    	end();
    }
}
