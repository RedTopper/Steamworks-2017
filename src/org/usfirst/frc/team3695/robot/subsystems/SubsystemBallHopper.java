package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemBallHopper extends Subsystem {
	Solenoid openHopper;
	Solenoid closeHopper;
	boolean isOpen = false;
	
	public SubsystemBallHopper(){
		openHopper = new Solenoid(Constants.OPEN_BALL_HOPPER);
		closeHopper = new Solenoid(Constants.CLOSE_BALL_HOPPER);
	}
	
	public void closeHopper(){
		openHopper.set(false);
		closeHopper.set(true);
		isOpen = false;
	}
	
	public void openHopper(){
		openHopper.set(true);
		closeHopper.set(false);
		isOpen = true;
	}
	
	public void setState(boolean open){
		if (open)
			openHopper();
		else
			closeHopper();
	}
	
	public void toggle(){
		setState(!isOpen);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

