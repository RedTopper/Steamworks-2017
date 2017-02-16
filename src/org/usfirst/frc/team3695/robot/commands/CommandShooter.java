package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Controller;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandShooter extends Command {

    public CommandShooter() {
        requires(Robot.SUB_SHOOTER);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.SUB_SHOOTER.spin(Controller.OP_JOY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
