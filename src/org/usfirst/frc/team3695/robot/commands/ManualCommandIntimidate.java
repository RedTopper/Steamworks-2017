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
	int speed;
	
	public ManualCommandIntimidate() { 
		requires(Robot.SUB_BLINGY); 
		r = 0; g = 0; b = 0; count = 0; 
	}

	protected void initialize() { color = Color.RED; speed = 1; }
	
	protected void execute() {
		for (int i = 0; i < speed; i++) {
			switch (color) {
				case RED: //255, 0, 0
					if (count++ > 255) { color = Color.ORANGE; count = 0; } else {
						r = 255;
						g = 0;
						b--;
					}
					break;
				case ORANGE: //255, 127, 0
					if (count++ > 255) { color = Color.YELLOW; count = 0; } else {
						r = 255;
						g = count / 2;
						b = 0;
					}	
					break;
				case YELLOW: //255, 255, 0
					if (count++ > 255) { color = Color.GREEN; count = 0; } else {
						r = 255;
						g = (count / 2) + 127;
						b = 0;
					}
					break;
				case GREEN: //0, 255, 0
					if (count++ > 255) { color = Color.BLUE; count = 0; } else {
						r--;
						g = 255;
						b = 0;
					}
					break;
				case BLUE: //0, 0, 255
					if (count++ > 255) { color = Color.PURPLE; count = 0; } else {
						r = 0;
						g--;
						b++;
					}
					break;
				case PURPLE: //255, 0 , 255
					if (count++ > 255) { color = Color.RED; count = 0; } else {
						r++;
						g = 0;
						b = 255;
					}
					break;
			}
		}
    	Robot.SUB_BLINGY.RGB(r,g,b);
    }
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {}

    protected void interrupted() {}
}
