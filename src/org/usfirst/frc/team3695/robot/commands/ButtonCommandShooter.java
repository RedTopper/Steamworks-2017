package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Direction;
import org.usfirst.frc.team3695.robot.enumeration.Feeder;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Enables and disables the shooter
 */
public class ButtonCommandShooter extends Command {

	private final Direction direction;
	private final Feeder feed;
	
    public ButtonCommandShooter(Direction direction, Feeder feed) {
        requires(Robot.SUB_SHOOTER);
        this.direction = direction;
        this.feed = feed;
    }

    protected void initialize() {
    	Robot.SUB_SHOOTER.spin(direction);
    	Robot.SUB_SHOOTER.feed(feed);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.SUB_SHOOTER.spin(Direction.NONE);
    	Robot.SUB_SHOOTER.feed(Feeder.NOT);
    }

    protected void interrupted() {
    	end();
    }
}
