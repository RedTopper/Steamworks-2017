package org.usfirst.frc.team3695.robot.commands;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Camera;
import org.usfirst.frc.team3695.robot.enumeration.Video;
import org.usfirst.frc.team3695.robot.subsystems.SubsystemDrive;
import org.usfirst.frc.team3695.robot.util.Cross;
import org.usfirst.frc.team3695.robot.util.Util;

import edu.wpi.first.wpilibj.command.Command;

public class CommandForward extends Command {
	private boolean finished = false;
	private final Cross object = new Cross("object", (Camera.WIDTH / 2.0) + 20.0, -1);
	private final Cross setpoint = new Cross("minumum", Camera.WIDTH / 2.0, 10);
	
	public CommandForward() {
		requires(Robot.SUB_DRIVE);
		Robot.VISION.putCrosshair(object);
		Robot.VISION.putCrosshair(setpoint);
	}
	
    protected void initialize() {
		Robot.VISION.setCamera(Camera.FRONT, Video.THRESHHOLD);
		object.setEnabled(true);
		setpoint.setEnabled(true);
		finished = false;
    }

    protected void execute() {
		Rect rect0;
		ArrayList<MatOfPoint> data;
		synchronized (Robot.GRIP) {
			data = Robot.GRIP.convexHullsOutput();
			if(data.size() < 1) {
				finished = true;
				return;
			}
			rect0 = Imgproc.boundingRect(data.get(0));
		}
		double y = rect0.y + (rect0.height / 2);
		object.setXY(object.getX(), y);
		
		if(y < setpoint.getY()) {
			finished = true;
		} else {
			double speed = SubsystemDrive.ips2rpm(Util.getAndSetDouble("CAMERA: forward in-s", 20.0));
			Robot.SUB_DRIVE.driveDirect(speed, speed);
		}
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
    	Robot.SUB_DRIVE.driveDirect(0, 0);
    	object.setEnabled(false);
    	setpoint.setEnabled(false);
    }

    protected void interrupted() {
    	end();
    }
}
