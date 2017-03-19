package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Camera;
import org.usfirst.frc.team3695.robot.enumeration.Video;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Enables and disables the shooter
 */
public class ButtonCommandCamera extends InstantCommand {
	
	private final Camera camera;
	
	public ButtonCommandCamera(Camera camera) {
		this.camera = camera;
	}
	
    protected void initialize() {
    	Robot.VISION.setCamera(camera, Video.RAW);
    }
}
