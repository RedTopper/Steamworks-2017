package org.usfirst.frc.team3695.robot.auto;

import org.usfirst.frc.team3695.robot.commands.CommandDistance;
import org.usfirst.frc.team3695.robot.commands.CommandError;
import org.usfirst.frc.team3695.robot.commands.CommandRotate;
import org.usfirst.frc.team3695.robot.enumeration.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRight extends CommandGroup {
	public AutoRight() {
		addSequential(new CommandDistance(5.0));
		addSequential(new CommandRotate(Autonomous.GEAR_LEFT));
		addSequential(new CommandError());
		//addSequential(new CommandGearFlaps(Piston.OPEN));
		//addSequential(new CommandDistance(rotations));
	}
}
