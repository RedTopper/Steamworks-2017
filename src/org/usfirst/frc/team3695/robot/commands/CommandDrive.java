package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Controller;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandDrive extends Command {

    public CommandDrive() {
        requires(Robot.SUB_DRIVE);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.SUB_DRIVE.dualStickDrive(Controller.DRIVE_JOY());;
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
