package org.usfirst.frc.team3695.robot.vision;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDVision implements PIDSource {
	
	boolean canSee = false;
	
	public void setPIDSourceType(PIDSourceType pidSource) {}
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	/**
	 * Locates the center of two glowing tape things and returns the center.
	 * The units of this PID subsystem are in pixels. The center is Vision.CAM_WIDTH / 2, and if it
	 * is not found, the method returns Vision.CAM_WIDTH.
	 */
	public double pidGet() {
		Rect rect0;
		Rect rect1;
		ArrayList<MatOfPoint> data;
		synchronized (Robot.GRIP) {
			data = Robot.GRIP.convexHullsOutput();
			if(data.size() < 2) {
				canSee = false;
				return 0;
			}
			canSee = true;
			rect0 = Imgproc.boundingRect(data.get(0));
			rect1 = Imgproc.boundingRect(data.get(1));
		}
		int x0 = rect0.x + (rect0.width / 2);
		int x1 = rect1.x + (rect1.width / 2);
		int center = (x0 + x1) / 2;
		SmartDashboard.putNumber("PIDCount", data.size());
		SmartDashboard.putNumber("PIDVision Position", center);
		return center;
	}
	
	public boolean canSee() {
		return canSee;
	}
	
	

}
