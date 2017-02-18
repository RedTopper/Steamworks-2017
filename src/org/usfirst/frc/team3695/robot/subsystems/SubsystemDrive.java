package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.commands.ManualCommandDrive;
import org.usfirst.frc.team3695.robot.enumeration.Xbox;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemDrive extends Subsystem {
	
	/**
	 * The converter ratio from Magic Velocity Units to Revolutions per Second.
	 */
	public static final double VELOCITY_SCALAR 
			= (1.0/20.0) //Encoder ticks
			* (1.0/2.0) //Channels
			* (3.0/14.0) //Gear-box Ratio 
			* 10.0; //from 1/10 of a second to 1 second;
	
	/**
	 * The maximum RPM that the drivers are allowed to drive at. 
	 * With an 8 in diam wheel, and if this is set to 5, that would convert
	 * to 40.0 * Math.PI in / sec, or about 10.47 feet per second.s
	 */
	public static final double MAX_RPM = 5;
	
	/**
	 * Diameter of the wheel in inches
	 */
	public static final double WHEEL_DIAM_INCHES = 8;
	
	private CANTalon left1;
	private CANTalon left2;
    private CANTalon right1;
    private CANTalon right2;

    public void initDefaultCommand() {
    	setDefaultCommand(new ManualCommandDrive());
    }
    
    public static final double magic2ips(double magic) {
    	
    }
    
    public static final double 
    
    /**
     * Initialize Needed Drivetrain Variables
     */
    public SubsystemDrive(){
    	//Master Talons
    	left1 = new CANTalon(Constants.LEFT_MOTOR);
    	right1 = new CANTalon(Constants.RIGHT_MOTOR);
    	//Slave Talons
    	left2 = new CANTalon(Constants.OTHER_LEFT_MOTOR);
    	right2 = new CANTalon(Constants.OTHER_RIGHT_MOTOR);
    	//Train the Slaves
    	left2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	left2.set(left1.getDeviceID());
    	right2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	right2.set(right1.getDeviceID());
    }
    
    public void dualStickDrive(Joystick joy){
    	double left = Xbox.LEFT_Y(joy);
    	double right = Xbox.RIGHT_Y(joy);
    	left1.set(left);
    	right1.set(right);
    }
}

