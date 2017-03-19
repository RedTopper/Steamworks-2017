package org.usfirst.frc.team3695.robot.vision;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDVision implements PIDSource {
	
	public static final int MIN_SIZE = 25;
	
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
		int center = 0;
		synchronized (Robot.GRIP) {
			List<Rect> data = getRekt();
			if(data.size() == 0) return 0;
			if(data.size() == 1) {
				Rect rect = data.get(0);
				center = rect.x + (rect.width / 2);
			}
			if(data.size() > 1) {
				Rect rect0 = data.get(0);
				Rect rect1 = data.get(1);
				int x0 = rect0.x + (rect0.width / 2);
				int x1 = rect1.x + (rect1.width / 2);
				center = (x0 + x1) / 2;
			}

			SmartDashboard.putNumber("Object Count", data.size());
			SmartDashboard.putNumber("Object Position", center);
		}
		return center;
	}
	
	public boolean canSee() {
		synchronized (Robot.GRIP) {
			return getRekt().size() > 0;
		}
	}
	
	public List<Rect> getRekt() {
		synchronized (Robot.GRIP) {
			List<Rect> screened = new ArrayList<>();
			List<MatOfPoint> found = Robot.GRIP.convexHullsOutput();
			for(MatOfPoint point : found) {
				Rect rect = Imgproc.boundingRect(point);
				if(rect.area() > MIN_SIZE) {
					screened.add(rect);
				}
			}
			return screened;
		}
	}
}
