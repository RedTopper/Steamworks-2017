//TODO make rainbow-blend color changing mode
//TODO set color change speed to 

package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.enumeration.Color;
import org.usfirst.frc.team3695.robot.OI;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualCommandIntimidate extends Command {
	Color color;
	
	public ManualCommandIntimidate() { requires(Robot.SUB_BLINGY); }

	protected void initialize() { color = Color.RED; }
	
	protected void execute() {
		String currentColor = color;
		switch (color) {
		case RED:
			
			break;
		case ORANGE:
			
			break;
		case YELLOW:
			
			break;
		case GREEN:
			
			break;
		case BLUE:
			
			break;
		case PURPLE:
			
			break;
			
		}
    	Robot.SUB_BLINGY.RGB(a,b,x);
    }
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {}

    protected void interrupted() {}
}
