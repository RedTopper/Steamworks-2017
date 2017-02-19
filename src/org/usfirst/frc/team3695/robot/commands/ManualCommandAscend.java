package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.OI;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualCommandAscend extends Command {
	
    public ManualCommandAscend() {
    	requires(Robot.SUB_ASCEND);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.SUB_ASCEND.climb(OI.OPERATOR);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
