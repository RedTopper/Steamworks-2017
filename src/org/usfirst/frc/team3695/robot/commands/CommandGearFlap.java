package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Flap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class CommandGearFlap extends InstantCommand {
	
	private final Flap flap;
	
	public CommandGearFlap(Flap flap) {
		this.flap = flap;
        requires(Robot.SUB_FUEL_FLAPS);
    }

    protected void initialize() {
    	switch(flap) {
    	case OPEN:
    		Robot.SUB_GEAR_FLAPS.openFlaps();
    		break;
    	case CLOSE:
    		Robot.SUB_GEAR_FLAPS.closeFlaps();
    		break;
    	}
    }
}
