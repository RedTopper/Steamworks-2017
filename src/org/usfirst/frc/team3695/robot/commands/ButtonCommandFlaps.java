package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Commands the gear flaps
 */
public class ButtonCommandFlaps extends Command {
	
    public ButtonCommandFlaps() {
        requires(Robot.SUB_GEAR_FLAPS);
    }

    protected void initialize() {
    	Robot.SUB_GEAR_FLAPS.openFlaps();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.SUB_GEAR_FLAPS.closeFlaps();
    }

    protected void interrupted() {
    	end();
    }
}
