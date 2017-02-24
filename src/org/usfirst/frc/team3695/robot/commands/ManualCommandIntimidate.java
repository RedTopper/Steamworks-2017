//TODO make rainbow-blend color changing mode
//TODO set color change speed to 

package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.enumeration.Color;
import org.usfirst.frc.team3695.robot.OI;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualCommandIntimidate extends Command {
	Color color;
	int count;
	int r,g,b;
	
	public ManualCommandIntimidate() { 
		requires(Robot.SUB_BLINGY); 
		r = 0; g = 0; b = 0; count = 0; 
	}

	protected void initialize() { color = Color.RED; }
	
	protected void execute() {
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
    	Robot.SUB_BLINGY.RGB(r,g,b);
    }
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {}

    protected void interrupted() {}
}
