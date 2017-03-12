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
		int center;
		synchronized (Robot.GRIP) {
			ArrayList<MatOfPoint> data = Robot.GRIP.convexHullsOutput();
			switch(data.size()) {
			case 1:
				Rect rect = Imgproc.boundingRect(data.get(0));
				center = rect.x + (rect.width / 2);
				break;
			case 2:
				Rect rect0 = Imgproc.boundingRect(data.get(0));
				Rect rect1 = Imgproc.boundingRect(data.get(1));
				int x0 = rect0.x + (rect0.width / 2);
				int x1 = rect1.x + (rect1.width / 2);
				center = (x0 + x1) / 2;
				break;
			default: 
				return 0;
			}

			SmartDashboard.putNumber("Object Count", data.size());
			SmartDashboard.putNumber("Object Position", center);
		}
		return center;
	}
	
	public boolean canSee() {
		synchronized (Robot.GRIP) {
			return Robot.GRIP.convexHullsOutput().size() > 0;
		}
	}
}
