package org.usfirst.frc.team3695.robot.commands;
import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3695.robot.Grip;
import org.usfirst.frc.team3695.robot.Robot;
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
	private static final int screenCenter = Vision.CAM_WIDTH / 2;
	
	/**
	 * Determines the +/- threshold in camera pixels, where the robot's rotation is seen as close enough to the center.
	 * Depending on how fast the robot will be turning to face the target, we may need to tweak this for accuracy.
	 */
	private static final int centerThreshold = 10;
	
	private boolean doneRotating = false;
	
	private Grip cameraPipeline;
	
    public CommandRotateToTarget(Grip pipeline) {
    	cameraPipeline = pipeline;
    }

    protected void initialize() {
    	requires(Robot.subsystemDrive);
    }

    protected void execute() {
    	synchronized (cameraPipeline) {
    		ArrayList<MatOfPoint> camData = cameraPipeline.convexHullsOutput();
    		// camData should be sorted from largest to smallest according to AJ.
    		// If this isn't the case, we might need to do a sort.
    		Rect rect0 = Imgproc.boundingRect(camData.get(0));
    		Rect rect1 = Imgproc.boundingRect(camData.get(1));
    		int x0 = rect0.x + (rect0.width / 2);
    		int x1 = rect1.x + (rect1.width / 2);
    		int targetCenter = (x0 + x1) / 2;
    		int diff = Math.abs(targetCenter - screenCenter);
    		if (diff <= centerThreshold) {
    			// TODO: Try to center the view from here (w/ PID?)
    			doneRotating = true;
    		}
    		else {
    			if (targetCenter < screenCenter) {
    				// TODO: Rotate left
    				
    			} else {
    				// TODO: Rotate right
    			}
    		}
    	}
    }

    protected boolean isFinished() {
        return doneRotating;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
