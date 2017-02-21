package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Opens and closes the rear flap of the robot to accept balls from the fuel hopper.
 */
public class SubsystemFuelFlaps extends Subsystem {
	private Solenoid openFlaps;
	private Solenoid closeFlaps;
	private boolean open; //Current State of flaps. True = Open, False = closed
	
	public SubsystemFuelFlaps(){
		openFlaps = new Solenoid(Constants.OPEN_BALL_HOPPER);
		closeFlaps = new Solenoid(Constants.CLOSE_BALL_HOPPER);
		open = false;
	}

    public void initDefaultCommand() {}
    
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
    	if (open) closeFlaps();	else openFlaps();
    }
}

