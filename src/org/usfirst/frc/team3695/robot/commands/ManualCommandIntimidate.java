//TODO make rainbow-blend color changing mode
//TODO set color change speed to 

package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.OI;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualCommandIntimidate extends Command {
	private static boolean finished;
	public ManualCommandIntimidate() {
        requires(Robot.SUB_BLINGY);
        
    }

	protected void initialize() {}
	
	protected void execute() {
    	Robot.SUB_DRIVE.dualStickDrive(OI.DRIVER);
    }
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {}

    protected void interrupted() {}
}
