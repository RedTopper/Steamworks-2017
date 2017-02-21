package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Direction;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Enables and disables the shooter
 */
public class ButtonCommandShooter extends Command {

	Direction direction;
	
    public ButtonCommandShooter(Direction direction) {
        requires(Robot.SUB_SHOOTER);
        this.direction = direction;
    }

    protected void initialize() {
    	Robot.SUB_SHOOTER.spin(direction);
    }

    protected void execute() {
    	Robot.SUB_SHOOTER.updateServo(direction);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.SUB_SHOOTER.spin(Direction.NONE);
    }

    protected void interrupted() {
    	end();
    }
}
