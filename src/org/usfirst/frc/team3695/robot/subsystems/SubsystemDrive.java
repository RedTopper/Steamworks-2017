package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.commands.ManualCommandDrive;
import org.usfirst.frc.team3695.robot.util.Xbox;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

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
	 * With an 8 in diameter wheel, and if this is set to 5, that would convert
	 * to 40.0 * Math.PI in / second, or about 10.47 feet per second.
	 */
	public static final double MAX_RPS = 5;
	
	/**
	 * The max speed of the robot, but in magic units
	 */
	public static final double MAX_MAGIC_SPEED = rps2magic(MAX_RPS);
	
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
    
    /** Converts a magic encoder value to inches per second */
    public static final double magic2ips(double magic) {
    	return magic * VELOCITY_SCALAR * WHEEL_DIAM_INCHES * Math.PI;
    }
    
    /** Converts an inches per second number to an encoder magic value */
    public static final double ips2magic(double ips) {
    	return ips / VELOCITY_SCALAR / WHEEL_DIAM_INCHES / Math.PI;
    }
    
    /** Converts a magic encoder value to revolutions per second */
    public static final double magic2rps(double magic) {
    	return magic * VELOCITY_SCALAR;
    }
    
    /** Converts revolutions per second to a magic encoder value */
    public static final double rps2magic(double rps) {
    	return rps / VELOCITY_SCALAR;
    }
    
    /**
     * Initialize Needed Drive-train Variables
     */
    public SubsystemDrive(){
    	//Master Talons
    	left1 = new CANTalon(Constants.LEFT_MOTOR);
    	right1 = new CANTalon(Constants.RIGHT_MOTOR);
    	left1.changeControlMode(TalonControlMode.Speed);
    	right1.changeControlMode(TalonControlMode.Speed);
    	
    	//Slave Talons
    	left2 = new CANTalon(Constants.OTHER_LEFT_MOTOR);
    	right2 = new CANTalon(Constants.OTHER_RIGHT_MOTOR);
    	//Train the Masters
    	left1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	right1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	left1.setEncPosition(0);
    	right1.setEncPosition(0);
    	//Train the Slaves
    	left2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	left2.set(left1.getDeviceID());
    	right2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	right2.set(right1.getDeviceID());
    }
    
    public void dualStickDrive(Joystick joy){
    	double left = Xbox.LEFT_Y(joy);
    	double right = Xbox.RIGHT_Y(joy);
    	left1.set(left * MAX_MAGIC_SPEED * (Constants.LEFT_MOTOR_INVERT ? -1.0 : 1.0));
    	right1.set(right * MAX_MAGIC_SPEED  * (Constants.RIGHT_MOTOR_INVERT ? -1.0 : 1.0));
    }
    
    public void directDrive(double left, double right) {
    	left1.set(left * (Constants.LEFT_MOTOR_INVERT ? -1.0 : 1.0));
    	right1.set(right  * (Constants.RIGHT_MOTOR_INVERT ? -1.0 : 1.0));
    }
}

