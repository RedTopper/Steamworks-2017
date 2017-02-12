package org.usfirst.frc.team3695.robot.commands;

import java.util.Random;

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
    	try {
			Thread.sleep(interval.nextInt(1000));
			int mode = interval.nextInt(3);
			if (mode == 0) {
				// short pulse
				for (int i = 1; i <= 100; i++) { Robot.subsystemShooter.spin(((((double)i)/10.00)*((double)i)/10.00)/100); Thread.sleep(7); }
				for (int i = 100; i > 0; i--) { Robot.subsystemShooter.spin(((((double)i)/10.00)*((double)i)/10.00)/100); Thread.sleep(4); }
			}
			else if (mode == 1) {
				// long pulse
				for (int i = 1; i <= 100; i++) { Robot.subsystemShooter.spin(((((double)i)/10.00)*((double)i)/10.00)/100); Thread.sleep(5); }
				Thread.sleep(500);
				for (int i = 100; i > 0; i--) { Robot.subsystemShooter.spin(((((double)i)/10.00)*((double)i)/10.00)/100); Thread.sleep(5); }
			}
			else {
				// double short pulse
				for (int i = 1; i <= 100; i++) { Robot.subsystemShooter.spin(((((double)i)/10.00)*((double)i)/10.00)/100); Thread.sleep(3); }
				for (int i = 100; i > 0; i--) { Robot.subsystemShooter.spin(((((double)i)/10.00)*((double)i)/10.00)/100); Thread.sleep(2); }
				
				for (int i = 1; i <= 100; i++) { Robot.subsystemShooter.spin(((((double)i)/10.00)*((double)i)/10.00)/100); Thread.sleep(4); }
				for (int i = 100; i > 0; i--) { Robot.subsystemShooter.spin(((((double)i)/10.00)*((double)i)/10.00)/100); Thread.sleep(2); }
			}
		} catch (InterruptedException e) { e.printStackTrace(); }
		
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
