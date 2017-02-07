package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Code that kills Pnuematics until interruption
 */
public class CommandKillCompressor extends Command {

    public CommandKillCompressor() {
        requires(Robot.subsystemCompressor);
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
