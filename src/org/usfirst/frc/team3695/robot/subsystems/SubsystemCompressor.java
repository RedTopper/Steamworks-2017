package org.usfirst.frc.team3695.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemCompressor extends Subsystem {

    Compressor comp = new Compressor();

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean isEnabled(){
		return comp.enabled();
    }
}

