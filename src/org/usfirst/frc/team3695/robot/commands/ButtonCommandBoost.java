package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Enables and disables the shooter
 */
public class ButtonCommandBoost extends Command {
	
    public ButtonCommandBoost() {}

    protected void initialize() {
    	Robot.SUB_DRIVE.enableSafety(false);
    	Robot.SUB_DRIVE.enableVbus(true);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.SUB_DRIVE.enableSafety(true);
    	Robot.SUB_DRIVE.enableVbus(false);
    }

    protected void interrupted() {
    	end();
    }
}
