package org.usfirst.frc.team3695.robot.auto;

import org.usfirst.frc.team3695.robot.commands.CommandDistance;
import org.usfirst.frc.team3695.robot.commands.CommandDriveUntilError;
import org.usfirst.frc.team3695.robot.commands.CommandGearFlap;
import org.usfirst.frc.team3695.robot.commands.CommandRotate;
import org.usfirst.frc.team3695.robot.commands.CommandWait;
import org.usfirst.frc.team3695.robot.enumeration.Autonomous;
import org.usfirst.frc.team3695.robot.enumeration.Direction;
import org.usfirst.frc.team3695.robot.enumeration.Flap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandGroupAuto extends CommandGroup {
		
	public static final long TIME_WAIT_FLAP = 250;
	
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
		case GEAR_RIGHT:
			addSequential(new CommandDistance(12.0 * 6.0 + 6.0));
			addSequential(new CommandRotate(auto));
			addSequential(new CommandDriveUntilError(Direction.FORWARD));
			addSequential(new CommandGearFlap(Flap.OPEN));
			addSequential(new CommandWait(TIME_WAIT_FLAP));
			addSequential(new CommandDistance(-(12.0 * 2.0)));
			break;
		case GEAR_CENTER:
			addSequential(new CommandDistance(12.0 * 3.0));
			addSequential(new CommandRotate(auto));
			addSequential(new CommandDriveUntilError(Direction.FORWARD));
			addSequential(new CommandGearFlap(Flap.OPEN));
			addSequential(new CommandWait(TIME_WAIT_FLAP));
			addSequential(new CommandDriveUntilError(Direction.BACKWARD));
			break;
		case GEAR_RIGHT_SHOOT:
			break;
		}
	}
}
