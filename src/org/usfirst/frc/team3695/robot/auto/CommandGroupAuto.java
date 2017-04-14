package org.usfirst.frc.team3695.robot.auto;

import org.usfirst.frc.team3695.robot.commands.CommandDistance;
import org.usfirst.frc.team3695.robot.commands.CommandDriveUntilError;
import org.usfirst.frc.team3695.robot.commands.CommandGearFlap;
import org.usfirst.frc.team3695.robot.commands.CommandRotate;
import org.usfirst.frc.team3695.robot.commands.CommandRotateDegrees;
import org.usfirst.frc.team3695.robot.commands.CommandWait;
import org.usfirst.frc.team3695.robot.enumeration.Autonomous;
import org.usfirst.frc.team3695.robot.enumeration.Direction;
import org.usfirst.frc.team3695.robot.enumeration.Flap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandGroupAuto extends CommandGroup {
		
	public static final long TIME_WAIT_FLAP = 500;
	
	public CommandGroupAuto(Autonomous auto) {
				
		//make sure dats gear flap is closed.
		addSequential(new CommandGearFlap(Flap.CLOSE));

		switch(auto) {
		case NOTHING:
			break;
		case FORWARD:
			addSequential(new CommandDistance(12.0 * 8.0));
			break;
		case GEAR_LEFT: 
		case GEAR_LEFT_RUN:
		case GEAR_RIGHT:
		case GEAR_RIGHT_RUN:
			addSequential(new CommandDistance(12.0 * 6.0));
			addSequential(new CommandRotate(auto));
			addSequential(new CommandDriveUntilError(Direction.FORWARD));
			addSequential(new CommandGearFlap(Flap.OPEN));
			addSequential(new CommandWait(TIME_WAIT_FLAP));
			addSequential(new CommandDistance(-(12.0 * 3.0)));
			break;
		case GEAR_CENTER:
			addSequential(new CommandDistance(12.0 * 3.0));
			addSequential(new CommandRotate(auto));
			addSequential(new CommandDriveUntilError(Direction.FORWARD));
			addSequential(new CommandGearFlap(Flap.OPEN));
			addSequential(new CommandWait(TIME_WAIT_FLAP));
			addSequential(new CommandDriveUntilError(Direction.BACKWARD));
			break;
		}
		
		switch(auto) {
		case GEAR_RIGHT_RUN:
			addSequential(new CommandRotateDegrees(55.0));
			addSequential(new CommandDistance(12.0 * 15.0));
			break;
		case GEAR_LEFT_RUN:
			addSequential(new CommandRotateDegrees(-55.0));
			addSequential(new CommandDistance(12.0 * 15.0));
			break;
		default:
			break;
		}
	}
}
