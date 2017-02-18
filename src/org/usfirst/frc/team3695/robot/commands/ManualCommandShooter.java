package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.OI;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualCommandShooter extends Command {

    public ManualCommandShooter() {
        requires(Robot.SUB_SHOOTER);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.SUB_SHOOTER.spin(OI.OPERATOR);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
