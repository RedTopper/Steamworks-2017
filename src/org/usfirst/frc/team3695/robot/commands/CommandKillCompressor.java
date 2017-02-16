package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Code that kills Pnuematics until interruption
 */
public class CommandKillCompressor extends Command {

    public CommandKillCompressor() {
        requires(Robot.SUB_COMPRESSOR);
    }

    protected void initialize() {
    	Robot.SUB_COMPRESSOR.setState(false);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.SUB_COMPRESSOR.setState(true);
    }

    protected void interrupted() {
    	end();
    }
}
