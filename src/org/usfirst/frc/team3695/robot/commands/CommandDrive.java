package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Xbox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandDrive extends Command {

	private final Joystick joy;
	
    public CommandDrive(Joystick joy) {
        requires(Robot.SUB_DRIVE);
        this.joy = joy;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.SUB_DRIVE.tankDrive(Xbox.LEFT_Y(joy), Xbox.RIGHT_Y(joy));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
