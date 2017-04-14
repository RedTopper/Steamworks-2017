//TODO make rainbow-blend color changing mode
//TODO set color change speed to 

package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.util.Color;
import org.usfirst.frc.team3695.robot.util.Util;

import edu.wpi.first.wpilibj.command.Command;

public class CommandIntimidate extends Command {
	
	public static final double SCALAR = 5.0/360.0;
	private Color color;
	private float hue;
	
	public CommandIntimidate() {
		requires(Robot.SUB_BLINGY); 
	}

	protected void initialize(int hue) { 
		this.hue = hue;
	}
	
	protected void execute() {
		hue += SCALAR * Util.getAndSetDouble("Intimidation Speed",  Constants.INTIMIDATION_SPEED);
		color = Color.getHSBColor(hue, 1, 0.5f);
		Robot.SUB_BLINGY.RGB(color.getRed(), color.getGreen(), color.getBlue());
    }
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {}

    protected void interrupted() {}
}
