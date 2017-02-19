package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ButtonCommandFlaps extends Command {
	
    public ButtonCommandFlaps() {
        requires(Robot.SUB_FLAPS);
    }

    protected void initialize() {
    	Robot.SUB_FLAPS.openFlaps();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.SUB_FLAPS.closeFlaps();
    }

    protected void interrupted() {
    	end();
    }
}
