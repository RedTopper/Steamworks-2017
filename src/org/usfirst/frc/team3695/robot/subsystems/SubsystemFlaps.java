package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemFlaps extends Subsystem {

	private Solenoid openFlaps;
	private Solenoid closeFlaps;
	
	public SubsystemFlaps(){
		openFlaps = new Solenoid(Constants.OPEN_GEAR_FLAPS);
		closeFlaps = new Solenoid(Constants.CLOSE_GEAR_FLAPS);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

