package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.commands.CommandIntimidate;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemBling extends Subsystem {
	PWM redChannel;
	PWM greenChannel;
	PWM blueChannel;
	
	public SubsystemBling(){
		redChannel = new PWM(Constants.RED_PWM);
		greenChannel = new PWM(Constants.GREEN_PWM);
		blueChannel = new PWM(Constants.BLUE_PWM);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new CommandIntimidate());
    }
    
    /**
     * Sets RGB of light in standard 0-255
     * @param r
     * @param b
     * @param g
     */
    public void RGB(int r, int g, int b){
    	redChannel.setRaw(r);
    	blueChannel.setRaw(b);
    	greenChannel.setRaw(g);
    }
    
    public void red() {
    	RGB(255, 0, 0);
    }
    
    public void blue() {
    	RGB(0, 255, 0);
    }
}

