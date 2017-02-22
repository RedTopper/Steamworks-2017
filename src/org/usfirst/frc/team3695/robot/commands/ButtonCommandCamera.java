package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Camera;
import org.usfirst.frc.team3695.robot.enumeration.Direction;
import org.usfirst.frc.team3695.robot.enumeration.Video;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Enables and disables the shooter
 */
public class ButtonCommandCamera extends Command {
	
    protected void initialize() {
    	Robot.VISION.setCamera(Camera.REAR, Video.RAW);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.VISION.setCamera(Camera.FRONT, Video.RAW);
    }

    protected void interrupted() {
    	end();
    }
}
