//TODO make rainbow-blend color changing mode
//TODO set color change speed to 

package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.util.Xbox;

import java.awt.Color;

import org.usfirst.frc.team3695.robot.OI;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualCommandIntimidate extends Command {
	
	public final static float SCALAR = (5f/360f);
	
	private Color color;
	private float hue;
	
	public ManualCommandIntimidate() {
		requires(Robot.SUB_BLINGY); 
	}

	protected void initialize() { 
		hue = 0;
	}
	
	protected void execute() {
		hue += (int) (SCALAR * Xbox.LT(OI.OPERATOR));
		color = Color.getHSBColor(hue, 1, 1);
		Robot.SUB_BLINGY.RGB(color.getRed(), color.getGreen(), color.getBlue());
    }
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {}

    protected void interrupted() {}
}
