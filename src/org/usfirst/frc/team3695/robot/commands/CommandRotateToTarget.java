package org.usfirst.frc.team3695.robot.commands;
import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3695.robot.Grip;
import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.util.Logger;
import org.usfirst.frc.team3695.robot.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Purpose: Makes the robot turn to face it's view between the two piece of reflective tape
 * @author Max G.
 */
public class CommandRotateToTarget extends Command {
	
	/**
	 * A constant description for the screen center
	 * In an ideal case, the camera center view should align w/ this.
	 */
	private static final int SCREEN_CENTER = Vision.CAM_WIDTH / 4;
	
	/**
	 * Determines the +/- threshold in camera pixels, where the robot's rotation is seen as close enough to the center.
	 * Depending on how fast the robot will be turning to face the target, we may need to tweak this for accuracy.
	 */
	private static final int CENTER_THRESHOLD = 10;
	
	private static final int SEARCH_TIMEOUT = 1200;
	
	private static final int LEFT  = -1;
	private static final int STILL =  0;
	private static final int RIGHT =  1;
	
	private boolean doneRotating = false;
	private Grip cameraPipeline;
	private int timeOutCounter = 0;
	private int debugLastRotNeeded = 0;
	
    public CommandRotateToTarget(Grip pipeline) {
    	requires(Robot.subsystemDrive);
    	cameraPipeline = pipeline;
    }
    
    protected void setTurn(int turnDir, double speed) {
    	double rot = (double)turnDir * speed;
    	Robot.subsystemDrive.tankDrive(rot, -rot);
    }

    protected void initialize() {
    	doneRotating = false;
    	timeOutCounter = 0;
    	Logger.out("Started rotator");
    }

    protected void execute() {
    	synchronized (cameraPipeline) {
    		// TODO: Try to utilize PID to help improve accuracy.
    		ArrayList<MatOfPoint> camData = cameraPipeline.convexHullsOutput();
    		int numConvexHulls = camData.size();
    		
    		// We need at least two pieces of reflective tape visible.
    		if (numConvexHulls < 2) { 
    			timeOutCounter++;
    			if (timeOutCounter % 100 == 0) {
    				Logger.out("timeOutCounter is at: " + timeOutCounter);
    			}
    			if (timeOutCounter > SEARCH_TIMEOUT) { 
    				Logger.err("Search timed out");
    				cancel(); // We couldn't find anything :(
    			} else { // Keep spinning around until we spot them.
    				setTurn(LEFT,0.4);
    			}
    		} else {
    			// Reset the timeout counter in case we need to check again.
    			timeOutCounter = 0;

	    		// camData should be sorted from largest to smallest according to AJ.
	    		// If this isn't the case, we might need to do a sort.
	    		Rect rect0 = Imgproc.boundingRect(camData.get(0));
	    		Rect rect1 = Imgproc.boundingRect(camData.get(1));
	    		int x0 = rect0.x + (rect0.width / 2);
	    		int x1 = rect1.x + (rect1.width / 2);
	    		int targetCenter = (x0 + x1) / 2;
	    		
	    		int rotNeeded = targetCenter - SCREEN_CENTER;
	    		int absRotNeeded = Math.abs(rotNeeded);
	    		if (absRotNeeded != debugLastRotNeeded)
	    		{
	    			debugLastRotNeeded = rotNeeded;
	    			Logger.out("ROT NEEDED CHANGED: " + rotNeeded + " X: " + targetCenter);
	    		}
	    		if (absRotNeeded <= CENTER_THRESHOLD) {
	    			doneRotating = true;
	    		}
	    		else {
	    			// As it gets within a near threshold of the center, the speed will reduce.
	    			// This might be a good substitute for PID? We'll see.
	    			if (rotNeeded > SCREEN_CENTER) {
	    				setTurn(LEFT,0.35);
	    			} else {
	    				setTurn(RIGHT,0.35);
	    			}
	    		}
    		}
    	}
    }

    protected boolean isFinished() {
        return doneRotating;
    }
    
    protected void stop() {
    	Logger.out("Autonomous Rotator is done");
    	setTurn(STILL,0);
    }

    protected void end() { stop(); }
    protected void interrupted() { stop(); }
    
}
