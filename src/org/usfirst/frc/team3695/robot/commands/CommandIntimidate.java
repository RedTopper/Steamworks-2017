package org.usfirst.frc.team3695.robot.commands;

import java.util.Random;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Vroom;

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
	double root;
	Vroom vroom;
	int pulseID;
	int runCount;
	long currentTime;
	long initAverage;
	long initCount;
	long runInitTime;
	long startTime;
	Random interval;

    public CommandIntimidate() {
    	requires(Robot.SUB_SHOOTER);
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
    			switch (vroom) {
	    			case WAITING: // waiting
	    				if (runInitTime == -1) {runInitTime = System.currentTimeMillis() - 1;}
	    				root = (double) ((currentTime - runInitTime) / initAverage);
	    				if (root >= 100) { isRunning = false; }
	    				break;
	    			case SHORT: // short pulse
	    				if (runInitTime == -1) {runInitTime = System.currentTimeMillis() - 1;}
	    				root = (double) ((currentTime - runInitTime) / initAverage);
	    				if (Math.pow(root, 2) / 100 <= 1) {
	    					Robot.SUB_SHOOTER.spin(Math.pow(root/10, 2)/100);
	    				} else { Robot.SUB_SHOOTER.spin(0); isRunning = false; }
	    				break;
	    			case LONG: // long pulse
	    				if (runInitTime == -1) {runInitTime = System.currentTimeMillis() - 1;}
	    				root = (double) ((currentTime - runInitTime) / initAverage);
	    				if (Math.pow(root, 2) / 100 <= 2) {
	    					Robot.SUB_SHOOTER.spin(Math.pow(root/10, 2)/200);
	    				} else { Robot.SUB_SHOOTER.spin(0); isRunning = false; } // probably won't work as planned, might need debugging on the 2/200 part
	    				break;
	    			case DOUBLE: // double pulse
	    				if (runInitTime == -1) {runInitTime = System.currentTimeMillis() - 1; pulseID = 0;}
	    				root = (double) ((currentTime - runInitTime) / initAverage);
	    				if (Math.pow(root, 2) / 100 <= 1) {
	    					Robot.SUB_SHOOTER.spin(Math.pow(root/10, 2)/100);
	    				} else { 
	    					if (pulseID == 0) {
	    						pulseID++;
	    						runInitTime = System.currentTimeMillis() - 1;
	    					} else { Robot.SUB_SHOOTER.spin(0); isRunning = false; }
	    				}
	    				break;
    			}
    		} else {
    			if (vroom == Vroom.WAITING) {
    				vroom = Vroom.values()[(int) (Math.random() * (double)(Vroom.values().length - 1)) + 1];
    				runInitTime = -1;
    				isRunning = true;
    			} else {
    				vroom = Vroom.WAITING;
    				runInitTime = -1;
    				isRunning = true;
    			}
    		}	
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
