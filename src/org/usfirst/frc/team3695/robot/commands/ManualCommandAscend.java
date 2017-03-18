package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.OI;
import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.util.Xbox;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualCommandAscend extends Command {

	private boolean lastVbusEnable = false;
	
    public ManualCommandAscend() {
    	requires(Robot.SUB_ASCEND);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.SUB_ASCEND.climb(OI.OPERATOR);
    	boolean vbusEnable = (Xbox.RT(OI.OPERATOR) > 0.25 || Xbox.LT(OI.OPERATOR) > 0.25);
    	if(vbusEnable != lastVbusEnable)  {
    		Robot.SUB_DRIVE.enableVbus(vbusEnable);
    		lastVbusEnable = vbusEnable;
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}