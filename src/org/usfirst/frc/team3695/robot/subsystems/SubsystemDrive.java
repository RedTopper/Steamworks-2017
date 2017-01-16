package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.Controller;
import org.usfirst.frc.team3695.robot.commands.CommandDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemDrive extends Subsystem {
	private Talon left;
    private Talon right;
    private RobotDrive roboDrive;

    public void initDefaultCommand() {
    	setDefaultCommand(new CommandDrive());
    }
    
    /**
     * Initialize Needed Drivetrain Variables
     */
    public SubsystemDrive(){
    	left = new Talon(Constants.LEFT_MOTOR);
    	right = new Talon(Constants.RIGHT_MOTOR);
    	roboDrive = new RobotDrive(left,right);
    }
    
    /**
     * Single Joystick drive
     */
    public void arcadeDrive(Joystick joy){
    	roboDrive.arcadeDrive(joy);
    }
    
    /**
     * RobotDrive TankDrive (Left Right Representaion)
     * @param left
     * @param right
     */
    public void tankdrive(double left, double right) {
		roboDrive.tankDrive(left,right);
	}
    
    /**
     * Rocket League Inspired Controls
     */
    public void rocketDrive(Joystick joy) {
		double[] tank = Controller.DRIVE_AXIS();
		tankdrive(tank[0],tank[1]);
	}
    
    public void straightLines(Joystick joy){
    	roboDrive.tankDrive(joy.getRawAxis(5), joy.getRawAxis(5));
    }
    
    public void dualStickDrive(Joystick joy){
    	roboDrive.tankDrive(joy.getRawAxis(1), joy.getRawAxis(5));
    }
}

