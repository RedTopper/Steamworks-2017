package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Controller;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandAscend extends Command {

    public CommandAscend() {
    	requires(Robot.subsystemAscend);
    	//requires(Robot.subsystemBallHopper);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.subsystemAscend.climb(Controller.OP_JOY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
