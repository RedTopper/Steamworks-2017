package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Controller;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandAscend extends Command {
	
	private final Joystick joy

    public CommandAscend(Joystick joy) {
    	requires(Robot.SUB_ASCEND);
    	//requires(Robot.subsystemBallHopper);
    	 this.joy = joy;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.SUB_ASCEND.climb(Controller.OP_JOY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
