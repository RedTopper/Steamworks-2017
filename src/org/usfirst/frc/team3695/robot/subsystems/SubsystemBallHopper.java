package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemBallHopper extends Subsystem {
	private Solenoid openFlaps;
	private Solenoid closeFlaps;
	private boolean open; //Current State of FLaps. True = Open, False = closed
	
	public SubsystemBallHopper(){
		openFlaps = new Solenoid(Constants.OPEN_BALL_HOPPER);
		closeFlaps = new Solenoid(Constants.CLOSE_BALL_HOPPER);
		open = false;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void openFlaps(){
    	openFlaps.set(true);
    	closeFlaps.set(false);
    	open = true;
    }
    
    public void closeFlaps(){
    	openFlaps.set(false);
    	closeFlaps.set(true);
    	open = false;
    }
    
    public void toggleFlaps(){
    	if (open)
    		closeFlaps();
    	else
    		openFlaps();
    }
}

