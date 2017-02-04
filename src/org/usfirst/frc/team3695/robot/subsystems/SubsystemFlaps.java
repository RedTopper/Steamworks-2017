package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem that controls the Gear Bearing Flaps
 */
public class SubsystemFlaps extends Subsystem {

	private Solenoid openFlaps;
	private Solenoid closeFlaps;
	private Solenoid loadingFlap;
	private boolean open; //Current State of FLaps. True = Open, False = closed
	
	public SubsystemFlaps(){
		openFlaps = new Solenoid(Constants.OPEN_GEAR_FLAPS);
		closeFlaps = new Solenoid(Constants.CLOSE_GEAR_FLAPS);
		loadingFlap = new Solenoid(Constants.CLOSE_GEAR_FEEDER_FLAPS);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void openFlaps(){
    	openFlaps.set(true);
    	closeFlaps.set(false);
    	loadingFlap.set(true);
    	open = true;
    }
    
    public void closeFlaps(){
    	openFlaps.set(false);
    	closeFlaps.set(true);
    	loadingFlap.set(false);
    	open = false;
    }
    
    public void toggleFlaps(){
    	if (open)
    		closeFlaps();
    	else
    		openFlaps();
    }
}

