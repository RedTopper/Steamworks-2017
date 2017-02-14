package org.usfirst.frc.team3695.robot.commands;

import java.util.Random;

import org.usfirst.frc.team3695.robot.commands.CommandShootTime;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
//TODO add descending code
//TODO add waiting code
//TODO add double pulse code
//TODO evaluate life

/**
 *
 */
public class CommandIntimidate extends Command {
	
	boolean ascending;
	boolean isRunning;
	int mode;
		// -1 = waiting
		//  0 = short pulse
		//  1 = long pulse
		//  2 = double short pulse
	int runCount;
	long currentTime;
	long initAverage;
	long initCount;
	long runInitTime;
	long startTime;
	Random interval;

    public CommandIntimidate() {
    	requires(Robot.subsystemShooter);
    }

    protected void initialize() {
    	initCount = 0;
    	interval = new Random();
    	runInitTime = -1;
    	startTime = System.currentTimeMillis();
    }

    protected void execute() {
    	if (initCount < 3) { initAverage += System.currentTimeMillis() - initAverage; initCount++; }
    	else if (initCount == 3) { initAverage /= 3; }
    	else {
    		currentTime = (System.currentTimeMillis() - startTime) / initAverage;
    		
    		if(isRunning) {
    			switch (mode) {
	    			case -1: // waiting
	    				if (runInitTime == -1) {runInitTime = System.currentTimeMillis() + 1;}
	    				
	    				break;
	    			case 0: // short pulse
	    				if (runInitTime == -1) {runInitTime = System.currentTimeMillis() + 1;}
	    				
	    				if (Math.pow((currentTime - runInitTime) / initAverage, 2) / 100 <= 1) {
	    					double root = (double) ((currentTime - runInitTime) / initAverage);
	    					Robot.subsystemShooter.spin(Math.pow(root/10, 2)/100);
	    				}
	    				
	    				break;
	    			case 1: // long pulse
	    				if (runInitTime == -1) {runInitTime = System.currentTimeMillis() + 1;}
	    				
	    				if (Math.pow((currentTime - runInitTime) / initAverage, 2) / 100 <= 2) {
	    					double root = (double) ((currentTime - runInitTime) / initAverage);
	    					Robot.subsystemShooter.spin(Math.pow(root/10, 2)/200);
	    				}
	    				
	    				break;
	    			case 2: // double pulse
	    				if (runInitTime == -1) {runInitTime = System.currentTimeMillis() + 1;}
	    				break;
    			}
    		}
    		else {
    			if (mode == -1) {
    				int mode = interval.nextInt(3);
    			} else {
    				
    			}
    		}
    		
    		
	    	
	    	
	    	///////////////////////////////////////////////////////// old code
    		new CommandShootTime(interval.nextInt(1000), 0);
			switch (mode) {
				case 0: // short pulse
					for (int i = 1; i <= 100; i++) { 
						double speed = Math.pow(((double)i)/10, 2)/100;
						new CommandShootTime(7, speed);}
					for (int i = 100; i > 0; i--) { 
						double speed = Math.pow(((double)i)/10, 2)/100;
						new CommandShootTime(4, speed);}
					Robot.subsystemShooter.spin(0);
					break;
				case 1: // long pulse
					for (int i = 1; i <= 100; i++) { 
						double speed = Math.pow(((double)i)/10, 2)/100;
						new CommandShootTime(5, speed);}
					new CommandShootTime(500, 1);
					for (int i = 100; i > 0; i--) { 
						double speed = Math.pow(((double)i)/10, 2)/100;
						new CommandShootTime(5, speed);}
					Robot.subsystemShooter.spin(0);
					break;
				case 2: // double short pulse
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
					break;
			}
			//////////////////////////////////////
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
