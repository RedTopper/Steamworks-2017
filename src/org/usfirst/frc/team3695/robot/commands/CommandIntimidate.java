package org.usfirst.frc.team3695.robot.commands;

import java.util.Random;

import org.usfirst.frc.team3695.robot.commands.CommandShootTime;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandIntimidate extends Command {

    public CommandIntimidate() {
    	requires(Robot.subsystemShooter);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Random interval = new Random();
    	new CommandShootTime(interval.nextInt(1000), 0);
		int mode = interval.nextInt(3);
		if (mode == 0) {
			// short pulse
			for (int i = 1; i <= 100; i++) { 
				double speed = Math.pow(((double)i)/10, 2)/100;
				new CommandShootTime(7, speed);}
			for (int i = 100; i > 0; i--) { 
				double speed = Math.pow(((double)i)/10, 2)/100;
				new CommandShootTime(4, speed);}
			Robot.subsystemShooter.spin(0);
		}
		else if (mode == 1) {
			// long pulse
			for (int i = 1; i <= 100; i++) { 
				double speed = Math.pow(((double)i)/10, 2)/100;
				new CommandShootTime(5, speed);}
			new CommandShootTime(500, 1);
			for (int i = 100; i > 0; i--) { 
				double speed = Math.pow(((double)i)/10, 2)/100;
				new CommandShootTime(5, speed);}
			Robot.subsystemShooter.spin(0);
		}
		else {
			// double short pulse
			for (int i = 1; i <= 100; i++) { 
				double speed = Math.pow(((double)i)/10, 2)/100;
				new CommandShootTime(3, speed);}
			for (int i = 100; i > 0; i--) { 
				double speed = Math.pow(((double)i)/10, 2)/100;
				new CommandShootTime(2, speed);}
			for (int i = 1; i <= 100; i++) { 
				double speed = Math.pow(((double)i)/10, 2)/100;
				new CommandShootTime(4, speed);}
			for (int i = 100; i > 0; i--) { 
				double speed = Math.pow(((double)i)/10, 2)/100;
				new CommandShootTime(2, speed);}
			Robot.subsystemShooter.spin(0);
		}
		
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
