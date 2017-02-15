package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Grip;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandRotate extends Command {
	
	final Grip grip;
	
	public CommandRotate(Grip grip) {
		this.grip = grip;
	}
	
    protected void initialize() {
    	Robot.subsystemCompressor.setState(false);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.subsystemCompressor.setState(true);
    }

    protected void interrupted() {
    	end();
    }
	
}
