package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandColor extends Command {
	private boolean finished;
	private int red;
	private int blue;
	private int green;

    public CommandColor(int red, int green, int blue) {
    	requires(Robot.SUB_BLINGY);
    	this.red = red;
    	this.green = green;
    	this.blue = blue;
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.SUB_BLINGY.RGB(red, green, blue);
    	finished = true;
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {}

    protected void interrupted() {}
}
